package mfaseeh.compose.valorantagents.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentResponseModel(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("displayName")
    val displayName: String,
    @SerialName("displayIcon")
    val displayIcon: String,
    @SerialName("isPlayableCharacter")
    val isPlayableCharacter: Boolean,
    @SerialName("description")
    val description: String,
    @SerialName("fullPortrait")
    val fullPortrait: String? = "",
    @SerialName("fullPortraitV2")
    val fullPortraitV2: String? = "",
    @SerialName("background")
    val background: String? = "",
    @SerialName("role")
    val role: Role? = null,
    @SerialName("abilities")
    val abilities: List<Ability>? = null
)

@Serializable
data class Ability(
    @SerialName("slot")
    val slot: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("displayIcon")
    val displayIcon: String? = ""
)

@Serializable
data class Role(
    @SerialName("uuid")
    val uuid: String = "",
    @SerialName("displayIcon")
    val displayIcon: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("assetPath")
    val assetPath: String = "",
    @SerialName("description")
    val description: String = ""
)

@Serializable
data class AgentListResponse(
    @SerialName("data")
    val data: List<AgentResponseModel>
)