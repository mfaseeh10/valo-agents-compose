package mfaseeh.compose.valorantagents.ui.home.uistates

import mfaseeh.compose.valorantagents.data.remote.model.Agent

internal sealed interface AgentDetailUiState {
    object Init : AgentDetailUiState
    object Loading : AgentDetailUiState
    data class Error(val message: String) : AgentDetailUiState

    object NoInternetConnection : AgentDetailUiState

    data class Success(
        val agentDetail: Agent,
    ) :
        AgentDetailUiState
}