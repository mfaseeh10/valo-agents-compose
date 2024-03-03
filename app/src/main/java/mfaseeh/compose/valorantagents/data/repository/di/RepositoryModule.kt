package mfaseeh.compose.valorantagents.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mfaseeh.compose.valorantagents.data.repository.AgentRepositoryImpl
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class  RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAgentRepository(
        agentRepositoryImpl: AgentRepositoryImpl
    ): AgentRepository
}