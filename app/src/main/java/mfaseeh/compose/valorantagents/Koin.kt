package mfaseeh.compose.valorantagents

import android.content.Context
import mfaseeh.compose.valorantagents.data.local.di.valoAgentsLocalModule
import mfaseeh.compose.valorantagents.data.remote.di.apiModule
import mfaseeh.compose.valorantagents.data.remote.di.networkModule
import mfaseeh.compose.valorantagents.data.remote.di.valoAgentsRemoteModule
import mfaseeh.compose.valorantagents.data.repository.di.repositoryModule
import mfaseeh.compose.valorantagents.domain.usecase.di.agentUseCaseModule
import mfaseeh.compose.valorantagents.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(context: Context){

    startKoin {
        androidContext(context)
        modules(
            listOf(
                appModule,
                networkModule,
                apiModule,
                valoAgentsLocalModule,
                valoAgentsRemoteModule,
                repositoryModule,
                agentUseCaseModule,
            )
        )
    }

}