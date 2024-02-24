package mfaseeh.compose.valorantagents.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.common.exception.NoInternetException
import mfaseeh.compose.valorantagents.common.getErrorMessage
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetails
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgents
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentDetailUiState
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentsListUiState
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getAgents: GetAgents,
    private val agentDetails: GetAgentDetails
) : ViewModel() {

    var agentsDetailsUiState: StateFlow<AgentDetailUiState> = MutableStateFlow(AgentDetailUiState.Init)

    fun onEvent(event: AgentUIEvent) {
        when (event) {
            is AgentUIEvent.OnClickAgent -> {
                agentsDetailsUiState = getAgentDetailsStream(event.uuid).stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(),
                    AgentDetailUiState.Loading
                )
                Log.d("AgentDetail", "${agentsDetailsUiState.value}")
            }
        }
    }

    val agentsListUiState: StateFlow<AgentsListUiState> =
        getAgentListStream().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            AgentsListUiState.Loading
        )

    private fun getAgentListStream() = getAgents.invoke().map { result ->
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
    }

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



}

sealed class AgentUIEvent {
    data class OnClickAgent(val uuid: String): AgentUIEvent()

}


