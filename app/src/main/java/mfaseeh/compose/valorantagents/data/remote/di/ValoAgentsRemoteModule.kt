package mfaseeh.compose.valorantagents.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDSImpl
import mfaseeh.compose.valorantagents.data.remote.source.api.ApiService

@Module
@InstallIn(SingletonComponent::class)
internal object ValoAgentsRemoteModule {
    @Provides
    fun provideAgentsRDS(service: ApiService): AgentsRDS {
        return AgentsRDSImpl(service)
    }
}