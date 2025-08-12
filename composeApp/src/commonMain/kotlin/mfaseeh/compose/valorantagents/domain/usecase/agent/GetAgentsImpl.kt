package mfaseeh.compose.valorantagents.domain.usecase.agent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.domain.model.toAgentUiModel
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository

class GetAgentsImpl (
    private val agentRepository: AgentRepository
) : GetAgents {
    override  fun invoke(): Flow<ResultState<List<AgentUiModel>>> =
        agentRepository.getAgents().map { result ->
            when (result) {
                is ResultState.Success -> {
                    ResultState.Success(result.data.map { it.toAgentUiModel() })
                }

                is ResultState.Error -> {
                    ResultState.Error(result.exception)
                }
            }
        }
}