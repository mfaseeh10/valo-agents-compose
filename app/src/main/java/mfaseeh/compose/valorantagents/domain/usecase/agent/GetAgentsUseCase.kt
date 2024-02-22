package mfaseeh.compose.valorantagents.domain.usecase.agent

import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.remote.model.Agent
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel

internal interface GetAgentsUseCase {
    fun invoke() : Flow<ResultState<List<AgentUiModel>>>
}