package mfaseeh.compose.valorantagents.ui.home.uistates

import mfaseeh.compose.valorantagents.domain.model.AgentUiModel

internal sealed interface AgentDetailUiState {
    object Init : AgentDetailUiState
    object Loading : AgentDetailUiState
    data class Error(val message: String) : AgentDetailUiState

    data class Success(
        val agentDetail: AgentUiModel,
    ) :
        AgentDetailUiState
}