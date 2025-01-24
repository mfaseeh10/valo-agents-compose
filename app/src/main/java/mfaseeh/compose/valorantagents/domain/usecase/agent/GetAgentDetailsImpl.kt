package mfaseeh.compose.valorantagents.domain.usecase.agent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.mapper.toAgentUiModel
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Inject

internal class GetAgentDetailsImpl @Inject constructor(
    private val agentRepository: AgentRepository
) : GetAgentDetails {
    override fun invoke(uuid: String): Flow<ResultState<AgentUiModel>> =
        agentRepository.getAgentDetails(uuid).map { result ->
            when (result) {
                is ResultState.Success -> {
                    ResultState.Success(result.data.toAgentUiModel())
                }
                is ResultState.Error -> {
                    result
                }
            }
        }
}
