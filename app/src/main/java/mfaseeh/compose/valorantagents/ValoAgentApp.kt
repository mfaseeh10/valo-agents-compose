package mfaseeh.compose.valorantagents

import android.app.Application

class ValoAgentApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this@ValoAgentApp)
    }
}