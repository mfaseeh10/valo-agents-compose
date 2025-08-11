package mfaseeh.compose.valorantagents.domain.model

data class AgentUiModel(
    val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val isPlayableCharacter: Boolean,
    val description: String,
    val fullPortrait: String,
    val fullPortraitV2: String,
    val background: String,
    val role: RoleUiModel,
    val abilities: List<AbilityUiModel>
)

data class RoleUiModel(
    val displayIcon: String = "",
    val displayName: String = "",
    val description: String = "",
)

data class AbilityUiModel(
    val slot: String = "",
    val displayIcon: String = "",
    val displayName: String = "",
    val description: String = "",
)