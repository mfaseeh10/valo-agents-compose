package mfaseeh.compose.valorantagents.domain.usecase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentsUseCase
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentsUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal object AgentUseCaseModule {
    @Provides
    fun provideGetAgentsUseCase(repository: AgentRepository): GetAgentsUseCase {
        return GetAgentsUseCaseImpl(repository)
    }

}