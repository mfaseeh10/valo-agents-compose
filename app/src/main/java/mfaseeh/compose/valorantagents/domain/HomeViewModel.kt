package mfaseeh.compose.valorantagents.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.common.exception.NoInternetException
import mfaseeh.compose.valorantagents.common.getErrorMessage
import mfaseeh.compose.valorantagents.data.model.Agent
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentsUseCase
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    agentsUseCase: GetAgentsUseCase
) : ViewModel() {

    val agentsListUiState: StateFlow<AgentsListUiState> =
        agentsUseCase.invoke().map { result ->
            when (result) {
                is ResultState.Success -> {
                    if (result.data.isEmpty()) {
                        AgentsListUiState.Error("No data")
                    } else {
                        AgentsListUiState.GetAgentsSuccess(result.data)
                    }
                }

                is ResultState.Error -> {
                    when (result.exception) {
                        is NoInternetException -> {
                            AgentsListUiState.NoInternetConnection
                        }

                        else -> {
                            AgentsListUiState.Error(result.getErrorMessage())
                        }
                    }
                }

            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            AgentsListUiState.Loading
        )

}


internal sealed interface AgentsListUiState {
    object Init : AgentsListUiState
    object Loading : AgentsListUiState
    data class Error(val message: String) : AgentsListUiState

    object NoInternetConnection : AgentsListUiState

    data class GetAgentsSuccess(
        val agents: List<Agent>,
    ) :
        AgentsListUiState
}