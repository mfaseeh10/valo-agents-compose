package mfaseeh.compose.valorantagents.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import mfaseeh.compose.valorantagents.data.model.Agent
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel  @Inject constructor(

) : ViewModel() {
    private val _agentsListUiState =
        MutableStateFlow<AgentsListUiState>(AgentsListUiState.Init)
    val agentsListUiState: StateFlow<AgentsListUiState> =
        _agentsListUiState



}


internal sealed interface AgentsListUiState {
    object Init : AgentsListUiState
    object Loading : AgentsListUiState
    data class Error(val message: String) : AgentsListUiState
    data class GetAgentsSuccess(
        val agents: List<Agent>,
    ) :
        AgentsListUiState
}