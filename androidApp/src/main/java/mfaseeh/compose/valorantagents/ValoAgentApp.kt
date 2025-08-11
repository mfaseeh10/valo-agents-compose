package mfaseeh.compose.valorantagents

import android.app.Application
import mfaseeh.compose.valorantagents.di.initKoin
import mfaseeh.compose.valorantagents.ui.di.androidAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules

class ValoAgentApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ValoAgentApp)
        }
        // Load Android-specific modules
        loadKoinModules(androidAppModule)
    }
}