package mfaseeh.compose.valorantagents

import androidx.compose.ui.window.ComposeUIViewController
import mfaseeh.compose.valorantagents.app.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
