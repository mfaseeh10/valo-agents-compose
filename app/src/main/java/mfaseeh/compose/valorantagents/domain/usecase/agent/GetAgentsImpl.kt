package mfaseeh.compose.valorantagents.domain.usecase.agent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.domain.model.toAgentUiModel
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Inject

internal class GetAgentsImpl @Inject constructor(
    private val agentRepository: AgentRepository
) : GetAgents {
    override fun invoke(): Flow<ResultState<List<AgentUiModel>>> = flow {
        agentRepository.getAgents().collect {
            if (it is ResultState.Success) {
                emit(ResultState.Success(
                    it.data.map { agent ->
                        agent.toAgentUiModel()
                    }
                ))
            } else if (it is ResultState.Error) {
                emit(it)
            }
        }
    }
}