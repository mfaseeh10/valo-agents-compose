package mfaseeh.compose.valorantagents.data.remote.source

import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.model.AgentResponse

internal interface AgentsRDS {
    fun fetchAgents(): Flow<ResultState<AgentResponse>>
}