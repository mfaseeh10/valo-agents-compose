package mfaseeh.compose.valorantagents.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Agent(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayIcon")
    val displayIcon: String,
)

@JsonClass(generateAdapter = true)
data class AgentResponse(
    @Json(name = "data")
    val data: List<Agent>
)