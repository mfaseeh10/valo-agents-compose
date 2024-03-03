package mfaseeh.compose.valorantagents.domain.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetails
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetailsImpl
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgents
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentsImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AgentUseCaseModule {
    @Binds
    @Singleton
    abstract fun provideGetAgentsUseCase(
        getAgentsImpl: GetAgentsImpl
    ): GetAgents

    @Binds
    @Singleton
    abstract fun provideGetAgentDetailsUseCase(
        getAgentDetailsImpl: GetAgentDetailsImpl
    ): GetAgentDetails


}