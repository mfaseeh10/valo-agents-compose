package mfaseeh.compose.valorantagents.ui.home.uistates

import mfaseeh.compose.valorantagents.domain.model.AgentUiModel

internal sealed interface AgentsListUiState {
    object Init : AgentsListUiState
    object Loading : AgentsListUiState
    data class Error(val message: String) : AgentsListUiState

    object NoInternetConnection : AgentsListUiState

    data class GetAgentsSuccess(
        val agents: List<AgentUiModel>,
    ) :
        AgentsListUiState
}
