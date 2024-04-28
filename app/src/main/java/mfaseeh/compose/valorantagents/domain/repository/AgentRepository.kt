package mfaseeh.compose.valorantagents.domain.repository

import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel

internal interface AgentRepository {

    suspend fun getAgents(): Flow<ResultState<List<AgentResponseModel>>>

    suspend fun getAgentDetails(uuid: String): Flow<ResultState<AgentEntity>>
}