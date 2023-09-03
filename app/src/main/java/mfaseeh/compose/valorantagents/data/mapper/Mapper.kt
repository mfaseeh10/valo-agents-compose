package mfaseeh.compose.valorantagents.data.mapper

import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity
import mfaseeh.compose.valorantagents.data.model.Agent

internal fun AgentEntity.toAgent() = Agent(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon
)

internal fun Agent.toAgentEntity() = AgentEntity(
    uuid = this.uuid,
    displayName = this.displayName,
    displayIcon = this.displayIcon
)