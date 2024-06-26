package mfaseeh.compose.valorantagents.data.remote.source

import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.remote.model.AgentListResponse

internal interface AgentsRDS {
    fun fetchAgents(): Flow<ResultState<AgentListResponse>>
}