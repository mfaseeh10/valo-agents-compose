package mfaseeh.compose.valorantagents.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AgentResponseModel(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayIcon")
    val displayIcon: String,
    @Json(name = "isPlayableCharacter")
    val isPlayableCharacter: Boolean,
    @Json(name = "description")
    val description: String,
    @Json(name = "fullPortrait")
    val fullPortrait: String? = "",
    @Json(name = "fullPortraitV2")
    val fullPortraitV2: String? = "",
    @Json(name = "background")
    val background: String? = "",
    @Json(name = "role")
    val role: Role? = null
)

@JsonClass(generateAdapter = true)
data class Role(
    @Json(name = "uuid")
    val uuid: String = "",
    @Json(name = "displayIcon")
    val displayIcon: String = "",
    @Json(name = "displayName")
    val displayName: String = "",
    @Json(name = "assetPath")
    val assetPath: String = "",
    @Json(name = "description")
    val description: String = ""
)

@JsonClass(generateAdapter = true)
data class AgentListResponse(
    @Json(name = "data")
    val data: List<AgentResponseModel>
)