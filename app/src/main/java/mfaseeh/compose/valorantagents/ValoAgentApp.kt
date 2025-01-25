package mfaseeh.compose.valorantagents

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ValoAgentApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}