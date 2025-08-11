package mfaseeh.compose.valorantagents.domain.usecase.agent

import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel

interface GetAgentDetails {
    fun invoke(uuid: String) : Flow<ResultState<AgentUiModel>>
}