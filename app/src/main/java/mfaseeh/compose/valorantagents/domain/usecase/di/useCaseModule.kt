package mfaseeh.compose.valorantagents.domain.usecase.di

import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetails
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentDetailsImpl
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgents
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentsImpl
import org.koin.dsl.module

val agentUseCaseModule = module {
    // Bind GetAgentsImpl to GetAgents
    single<GetAgents> { GetAgentsImpl(get()) } // Pass required dependencies using `get()`

    // Bind GetAgentDetailsImpl to GetAgentDetails
    single<GetAgentDetails> { GetAgentDetailsImpl(get()) } // Pass required dependencies using `get()`
}
