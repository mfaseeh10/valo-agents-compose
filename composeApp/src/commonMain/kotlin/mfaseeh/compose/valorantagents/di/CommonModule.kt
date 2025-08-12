package mfaseeh.compose.valorantagents.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDS
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDSImpl
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDSImpl
import mfaseeh.compose.valorantagents.data.remote.source.api.ApiService
import mfaseeh.compose.valorantagents.data.repository.AgentRepositoryImpl
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetails
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetailsImpl
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgents
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentsImpl
import org.koin.dsl.module

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
}

val apiModule = module {
    single {
        ApiService(get<HttpClient>())
    }
}

val remoteModule = module {
    single<AgentsRDS> {
        AgentsRDSImpl(get())
    }
}

val localModule = module {
    single<AgentsLDS> {
        AgentsLDSImpl(get())
    }
}

val repositoryModule = module {
    single<AgentRepository> {
        AgentRepositoryImpl(
            get(),
            get(),
        )
    }
}

val useCaseModule = module {
    single<GetAgents> { GetAgentsImpl(get()) }
    single<GetAgentDetails> { GetAgentDetailsImpl(get()) }
}