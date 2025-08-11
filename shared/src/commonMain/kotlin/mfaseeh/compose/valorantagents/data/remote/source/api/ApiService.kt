package mfaseeh.compose.valorantagents.data.remote.source.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import mfaseeh.compose.valorantagents.data.remote.model.AgentListResponse

class ApiService(private val httpClient: HttpClient) {
    
    suspend fun fetchAgents(): AgentListResponse {
        return httpClient.get("agents").body()
    }
}