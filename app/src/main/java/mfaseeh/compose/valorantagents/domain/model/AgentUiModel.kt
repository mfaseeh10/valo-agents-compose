package mfaseeh.compose.valorantagents.domain.model

import mfaseeh.compose.valorantagents.data.remote.model.Agent

data class AgentUiModel(
    val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val isPlayableCharacter: Boolean,
    val description: String,
    val fullPortrait: String,
    val fullPortraitV2: String,
    val background: String
)

internal fun Agent.toAgentUiModel() = AgentUiModel(
    uuid = uuid,
    displayName = displayName,
    displayIcon = displayIcon,
    isPlayableCharacter = isPlayableCharacter,
    description = description,
    fullPortrait = fullPortrait ?: "",
    fullPortraitV2 = fullPortraitV2 ?: "",
    background = background ?: ""
)