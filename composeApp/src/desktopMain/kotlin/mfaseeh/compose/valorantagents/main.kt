package mfaseeh.compose.valorantagents

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import mfaseeh.compose.valorantagents.app.App
import mfaseeh.compose.valorantagents.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ValorantAgents",
    ) {
        App()
    }
}
