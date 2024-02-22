package mfaseeh.compose.valorantagents.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.common.exception.NoInternetException
import mfaseeh.compose.valorantagents.common.getErrorMessage
import mfaseeh.compose.valorantagents.domain.usecase.agent.GetAgentsUseCase
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentsListUiState
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    agentsUseCase: GetAgentsUseCase
) : ViewModel() {

    val agentsListUiState: StateFlow<AgentsListUiState> =
        agentsUseCase.invoke().map { result ->
            when (result) {
                is ResultState.Success -> {
                    if (result.data.isEmpty()) {
                        AgentsListUiState.Error("No data")
                    } else {
                        AgentsListUiState.GetAgentsSuccess(result.data)
                    }
                }

                is ResultState.Error -> {
                    when (result.exception) {
                        is NoInternetException -> {
                            AgentsListUiState.NoInternetConnection
                        }

                        else -> {
                            AgentsListUiState.Error(result.getErrorMessage())
                        }
                    }
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            AgentsListUiState.Loading
        )

    val agentsDetailsUiState: StateFlow<AgentsListUiState> =
        agentsUseCase.invoke().map { result ->
            when (result) {
                is ResultState.Success -> {
                    if (result.data.isEmpty()) {
                        AgentsListUiState.Error("No data")
                    } else {
                        AgentsListUiState.GetAgentsSuccess(result.data)
                    }
                }

                is ResultState.Error -> {
                    when (result.exception) {
                        is NoInternetException -> {
                            AgentsListUiState.NoInternetConnection
                        }

                        else -> {
                            AgentsListUiState.Error(result.getErrorMessage())
                        }
                    }
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            AgentsListUiState.Loading
        )



}


