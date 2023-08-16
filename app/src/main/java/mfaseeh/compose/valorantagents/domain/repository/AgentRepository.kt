package mfaseeh.compose.valorantagents.domain.repository

import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.model.Agent

internal interface AgentRepository {

    suspend fun getAgents(): Flow<ResultState<List<Agent>>>
}