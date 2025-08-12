package mfaseeh.compose.valorantagents.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object HomeScreen : Screen

    @Serializable
    data class AgentDetail(val uuid: String) : Screen
}
