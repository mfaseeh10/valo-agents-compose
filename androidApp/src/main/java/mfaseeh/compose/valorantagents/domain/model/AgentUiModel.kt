package mfaseeh.compose.valorantagents.domain.model

import mfaseeh.compose.valorantagents.data.remote.model.Ability
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel
import mfaseeh.compose.valorantagents.data.remote.model.Role

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

internal fun AgentResponseModel.toAgentUiModel() = AgentUiModel(
    uuid = uuid,
    displayName = displayName,
    displayIcon = displayIcon,
    isPlayableCharacter = isPlayableCharacter,
    description = description,
    fullPortrait = fullPortrait ?: "",
    fullPortraitV2 = fullPortraitV2 ?: "",
    background = background ?: "",
    role = this.role?.toRoleUiModel() ?: RoleUiModel(),
    abilities = this.abilities?.toAbilitiesUiModelList() ?: listOf(AbilityUiModel())
)

internal fun Role.toRoleUiModel() = RoleUiModel(
    displayIcon = this.displayIcon,
    displayName = this.displayName,
    description = this.description
)
internal fun List<Ability>.toAbilitiesUiModelList() =
    this.map { it.toAbilityUiModel() }
internal fun Ability.toAbilityUiModel() = AbilityUiModel(
    slot = this.slot,
    displayIcon = this.displayIcon ?: "",
    displayName = this.displayName,
    description = this.description
)