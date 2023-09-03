package mfaseeh.compose.valorantagents.data.repository.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDS
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.data.remote.source.api.ApiService
import mfaseeh.compose.valorantagents.data.repository.AgentRepositoryImpl
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun provideAgentRepository(agentsRDS: AgentsRDS,agentsLDS: AgentsLDS, app: Application): AgentRepository {
        return AgentRepositoryImpl(agentsRDS,agentsLDS, app)
    }
}