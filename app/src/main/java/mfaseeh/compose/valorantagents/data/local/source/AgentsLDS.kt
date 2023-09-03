package mfaseeh.compose.valorantagents.data.local.source

import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.data.model.Agent

internal interface AgentsLDS {
    fun getAgents(): Flow<List<Agent>>

    fun getAgentId(id: String): Flow<Agent>

//    suspend fun saveAgents(stores: List<Agent>)

    suspend fun deleteAgents()

    suspend fun reInsertAgents(stores: List<Agent>)
}