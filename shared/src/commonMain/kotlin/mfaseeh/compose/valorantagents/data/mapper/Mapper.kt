package mfaseeh.compose.valorantagents.data.mapper

import mfaseeh.compose.valorantagents.data.local.entity.AbilitiesEntity
import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity
import mfaseeh.compose.valorantagents.data.local.entity.RoleEntity
import mfaseeh.compose.valorantagents.data.remote.model.Ability
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel
import mfaseeh.compose.valorantagents.data.remote.model.Role
import mfaseeh.compose.valorantagents.domain.model.AbilityUiModel
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.domain.model.RoleUiModel

fun AgentEntity.toAgentResponseModel() = AgentResponseModel(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    isPlayableCharacter= this.isPlayableCharacter,
    description = this.description,
    fullPortrait = this.fullPortrait,
    fullPortraitV2 = this.fullPortraitV2,
    background = this.background,
    role = this.roleEntity.toRole(),
    abilities = this.abilitiesEntity.toAbilitiesList()
)

fun AgentEntity.toAgentUiModel() = AgentUiModel(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    isPlayableCharacter= this.isPlayableCharacter,
    description = this.description,
    fullPortrait = this.fullPortrait.orEmpty(),
    fullPortraitV2 = this.fullPortraitV2.orEmpty(),
    background = this.background.orEmpty(),
    role = this.roleEntity.toRoleUIModel(),
    abilities = this.abilitiesEntity.toAbilitiesUIModelList()
)

fun AgentResponseModel.toAgentEntity() = AgentEntity(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    isPlayableCharacter= this.isPlayableCharacter,
    description = this.description,
    fullPortrait = this.fullPortrait,
    fullPortraitV2 = this.fullPortraitV2,
    background = this.background,
    roleEntity = this.role?.toRoleEntity() ?: RoleEntity(),
    abilitiesEntity = this.abilities?.toAbilitiesEntityList() ?: listOf(AbilitiesEntity())
)

fun List<Ability>.toAbilitiesEntityList() =
    this.map { it.toAbilityEntity() }

fun List<AbilitiesEntity>.toAbilitiesList() =
    this.map { it.toAbility() }

fun List<AbilitiesEntity>.toAbilitiesUIModelList() =
    this.map { it.toAbilityUiModel() }

fun Ability.toAbilityEntity() = AbilitiesEntity(
    slot = this.slot,
    displayName = this.displayName,
    description = this.description,
    displayIcon = this.displayIcon ?: ""
)

fun AbilitiesEntity.toAbility() = Ability(
    slot = this.slot,
    displayName = this.displayName,
    description = this.description,
    displayIcon = this.displayIcon
)

fun AbilitiesEntity.toAbilityUiModel() = AbilityUiModel(
    slot = this.slot,
    displayName = this.displayName,
    description = this.description,
    displayIcon = this.displayIcon
)

fun RoleEntity.toRole() = Role(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
)

fun RoleEntity.toRoleUIModel() = RoleUiModel(
    displayIcon = this.displayIcon,
    displayName = this.displayName,
    description = description,
)

fun Role.toRoleEntity() = RoleEntity(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    description = this.description
)