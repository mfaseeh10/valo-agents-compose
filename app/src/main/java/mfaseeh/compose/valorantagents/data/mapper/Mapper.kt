package mfaseeh.compose.valorantagents.data.mapper

import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity
import mfaseeh.compose.valorantagents.data.model.Agent

internal fun AgentEntity.toAgent() = Agent(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    isPlayableCharacter= this.isPlayableCharacter,
    description = this.description,
    fullPortrait = this.fullPortrait,
    fullPortraitV2 = this.fullPortraitV2,
    background = this.background
)

internal fun Agent.toAgentEntity() = AgentEntity(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon,
    isPlayableCharacter= this.isPlayableCharacter,
    description = this.description,
    fullPortrait = this.fullPortrait,
    fullPortraitV2 = this.fullPortraitV2,
    background = this.background
)