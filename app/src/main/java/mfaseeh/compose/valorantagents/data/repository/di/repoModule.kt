package mfaseeh.compose.valorantagents.data.repository.di

import mfaseeh.compose.valorantagents.data.repository.AgentRepositoryImpl
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import org.koin.dsl.module

val repositoryModule = module {
    // Bind AgentRepositoryImpl as the implementation for AgentRepository
    single<AgentRepository> {
        AgentRepositoryImpl(
            get(),
            get(),
        )
    }
}
