package mfaseeh.compose.valorantagents.data.mapper

import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity
import mfaseeh.compose.valorantagents.data.local.entity.RoleEntity
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel
import mfaseeh.compose.valorantagents.data.remote.model.Role
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.domain.model.RoleUiModel

internal fun AgentEntity.toAgent() = AgentResponseModel(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    isPlayableCharacter= this.isPlayableCharacter,
    description = this.description,
    fullPortrait = this.fullPortrait,
    fullPortraitV2 = this.fullPortraitV2,
    background = this.background,
    role = this.roleEntity.toRole()
)

internal fun AgentEntity.toAgentUiModel() = AgentUiModel(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    isPlayableCharacter= this.isPlayableCharacter,
    description = this.description,
    fullPortrait = this.fullPortrait.orEmpty(),
    fullPortraitV2 = this.fullPortraitV2.orEmpty(),
    background = this.background.orEmpty(),
    role = this.roleEntity.toRoleUIModel()
)

internal fun AgentResponseModel.toAgentEntity() = AgentEntity(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    isPlayableCharacter= this.isPlayableCharacter,
    description = this.description,
    fullPortrait = this.fullPortrait,
    fullPortraitV2 = this.fullPortraitV2,
    background = this.background,
    roleEntity = this.role?.toRoleEntity() ?: RoleEntity()
)

internal fun RoleEntity.toRole() = Role(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
)
internal fun RoleEntity.toRoleUIModel() = RoleUiModel(
    displayIcon = this.displayIcon,
    displayName = this.displayName,
    description = description,
)
internal fun Role.toRoleEntity() = RoleEntity(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    description = this.description
)