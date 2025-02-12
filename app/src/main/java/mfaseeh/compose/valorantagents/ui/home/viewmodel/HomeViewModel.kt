package mfaseeh.compose.valorantagents.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.common.exception.NoInternetException
import mfaseeh.compose.valorantagents.common.getErrorMessage
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgents
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentsListUiState

internal class HomeViewModel (
    private val getAgents: GetAgents,
) : ViewModel() {

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

}


