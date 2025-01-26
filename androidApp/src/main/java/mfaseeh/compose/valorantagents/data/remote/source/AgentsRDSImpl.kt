package mfaseeh.compose.valorantagents.data.remote.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.common.safeApiCall
import mfaseeh.compose.valorantagents.data.remote.model.AgentListResponse
import mfaseeh.compose.valorantagents.data.remote.source.api.ApiService

internal class AgentsRDSImpl(private val apiService: ApiService): AgentsRDS {
    override fun fetchAgents(): Flow<ResultState<AgentListResponse>> = flow {
        emit(
            safeApiCall {
                apiService.fetchAgents()
            }
        )
    }
}