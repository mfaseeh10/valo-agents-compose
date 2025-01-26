package mfaseeh.compose.valorantagents.data.remote.source.api

import mfaseeh.compose.valorantagents.data.remote.model.AgentListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("agents")
    suspend fun fetchAgents(): AgentListResponse
}