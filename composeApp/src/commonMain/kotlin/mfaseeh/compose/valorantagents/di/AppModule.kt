package mfaseeh.compose.valorantagents.di

import mfaseeh.compose.valorantagents.ui.agentdetails.AgentDetailViewModel
import mfaseeh.compose.valorantagents.ui.home.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::AgentDetailViewModel)
}
