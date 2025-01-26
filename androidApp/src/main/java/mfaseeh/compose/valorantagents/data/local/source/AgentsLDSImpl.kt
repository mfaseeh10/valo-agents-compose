package mfaseeh.compose.valorantagents.data.local.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import mfaseeh.compose.valorantagents.common.extensions.notNullable
import mfaseeh.compose.valorantagents.data.local.dao.AgentDao
import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity
import mfaseeh.compose.valorantagents.data.mapper.toAgentEntity
import mfaseeh.compose.valorantagents.data.mapper.toAgentResponseModel
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel

internal class AgentsLDSImpl (private val dao: AgentDao) : AgentsLDS {
    override fun getAgents(): Flow<List<AgentResponseModel>> = dao.getAllAgents().map {
        it.map { agentEntity -> agentEntity.toAgentResponseModel() }
    }

    override fun getAgentId(id: String): Flow<AgentEntity> =
        dao.getAgentById(id).distinctUntilChanged().notNullable()

//    override suspend fun saveAgents(stores: List<Agent>) {
//        TODO("Not yet implemented")
//    }

    override suspend fun deleteAgents() = dao.deleteAgents()

    override suspend fun reInsertAgents(agentResponseModels: List<AgentResponseModel>) {
        val filteredList = agentResponseModels.filterNot { it.background == null }
        dao.reInsertAgents(filteredList.map { it.toAgentEntity() })
    }

}