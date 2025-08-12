package mfaseeh.compose.valorantagents

import android.app.Application
import mfaseeh.compose.valorantagents.di.initKoin
import org.koin.android.ext.koin.androidContext

class ValoAgentApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ValoAgentApp)
        }
    }
}
