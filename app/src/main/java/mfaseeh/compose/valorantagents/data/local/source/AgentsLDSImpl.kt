package mfaseeh.compose.valorantagents.data.local.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import mfaseeh.compose.valorantagents.common.extensions.notNullable
import mfaseeh.compose.valorantagents.data.local.dao.AgentDao
import mfaseeh.compose.valorantagents.data.mapper.toAgent
import mfaseeh.compose.valorantagents.data.mapper.toAgentEntity
import mfaseeh.compose.valorantagents.data.model.Agent
import javax.inject.Inject

internal class AgentsLDSImpl @Inject constructor(val dao: AgentDao) : AgentsLDS {
    override fun getAgents(): Flow<List<Agent>> = dao.getAllAgents().map {
        it.map { agentEntity -> agentEntity.toAgent() }
    }

    override fun getAgentId(id: String): Flow<Agent> =
        dao.getAgentById(id).distinctUntilChanged().notNullable().map {
            it.toAgent()
        }

//    override suspend fun saveAgents(stores: List<Agent>) {
//        TODO("Not yet implemented")
//    }

    override suspend fun deleteAgents() = dao.deleteAgents()

    override suspend fun reInsertAgents(agents: List<Agent>) {
        val filteredList = agents.filterNot { it.background == null }
        dao.reInsertAgents(filteredList.map { it.toAgentEntity() })
    }

}