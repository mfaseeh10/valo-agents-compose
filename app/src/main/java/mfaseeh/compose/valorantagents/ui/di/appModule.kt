package mfaseeh.compose.valorantagents.ui.di

import mfaseeh.compose.valorantagents.ui.agentdetails.AgentDetailViewModel
import mfaseeh.compose.valorantagents.ui.home.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel {
        HomeViewModel(
            get()
        )
    }
    viewModel {
        AgentDetailViewModel(
            get()
        )
    }
}
