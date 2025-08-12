package mfaseeh.compose.valorantagents.data.local.source

import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel

interface AgentsLDS {
    fun getAgents(): Flow<List<AgentResponseModel>>
    fun getAgentId(id: String):  Flow<AgentEntity>
    suspend fun deleteAgents()
    suspend fun reInsertAgents(agentResponseModels: List<AgentResponseModel>)
}