package mfaseeh.compose.valorantagents.domain.usecase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetails
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetailsImpl
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgents
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentsImpl

@Module
@InstallIn(SingletonComponent::class)
internal object AgentUseCaseModule {
    @Provides
    fun provideGetAgentsUseCase(repository: AgentRepository): GetAgents {
        return GetAgentsImpl(repository)
    }

    @Provides
    fun provideGetAgentDetailsUseCase(repository: AgentRepository): GetAgentDetails {
        return GetAgentDetailsImpl(repository)
    }


}