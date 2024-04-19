package mfaseeh.compose.valorantagents.ui.home.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.common.exception.NoInternetException
import mfaseeh.compose.valorantagents.common.getErrorMessage
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetails
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentDetailUiState
import javax.inject.Inject

@HiltViewModel
internal class AgentDetailViewModel @Inject constructor(
    private val agentDetails: GetAgentDetails
) : ViewModel() {

    private val agentUuid = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val agentsDetailsUiState: StateFlow<AgentDetailUiState> =
        agentUuid.flatMapLatest { uuid -> getAgentDetailsStream(uuid) }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            AgentDetailUiState.Loading
        )

    private fun getAgentDetailsStream(uuid: String) = agentDetails.invoke(uuid).map { result ->
        when (result) {
            is ResultState.Success -> {
                AgentDetailUiState.Success(result.data)
            }

            is ResultState.Error -> {
                when (result.exception) {
                    is NoInternetException -> {
                        AgentDetailUiState.NoInternetConnection
                    }

                    else -> {
                        AgentDetailUiState.Error(result.getErrorMessage())
                    }
                }
            }
        }
    }

    fun getAgentDetails(uuid: String) {
        agentUuid.tryEmit(uuid)
    }


}


