package mfaseeh.compose.valorantagents.domain.usecase.agent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.remote.model.Agent
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.domain.model.toAgentUiModel
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Inject

internal class GetAgentDetailsImpl @Inject constructor(
    private val agentRepository: AgentRepository
) : GetAgentDetails {
    override fun invoke(uuid: String): Flow<ResultState<AgentUiModel>> = flow{
       agentRepository.getAgentDetails(uuid).collect {
           if (it is ResultState.Success) {
               emit(ResultState.Success(it.data.toAgentUiModel()))
           } else if (it is ResultState.Error) {
               emit(it)
           }
       }
    }
}