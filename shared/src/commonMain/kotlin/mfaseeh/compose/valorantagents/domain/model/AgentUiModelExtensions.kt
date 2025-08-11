package mfaseeh.compose.valorantagents.domain.model

import mfaseeh.compose.valorantagents.data.remote.model.Ability
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel
import mfaseeh.compose.valorantagents.data.remote.model.Role

fun AgentResponseModel.toAgentUiModel() = AgentUiModel(
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

fun Role.toRoleUiModel() = RoleUiModel(
    displayIcon = this.displayIcon,
    displayName = this.displayName,
    description = this.description
)

fun List<Ability>.toAbilitiesUiModelList() =
    this.map { it.toAbilityUiModel() }
    
fun Ability.toAbilityUiModel() = AbilityUiModel(
    slot = this.slot,
    displayIcon = this.displayIcon ?: "",
    displayName = this.displayName,
    description = this.description
)