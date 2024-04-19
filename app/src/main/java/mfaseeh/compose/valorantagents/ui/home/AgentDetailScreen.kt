package mfaseeh.compose.valorantagents.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentDetailUiState

@Composable
internal fun AgentDetail(agentDetailUiState: AgentDetailUiState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        when (agentDetailUiState) {
            is AgentDetailUiState.Error -> {
                TODO()
            }
            is AgentDetailUiState.Init -> {
                TODO()
            }
            is AgentDetailUiState.NoInternetConnection -> {
                TODO()
            }
            is AgentDetailUiState.Loading -> {
                Text(text = "Loading")
            }
            is AgentDetailUiState.Success -> {
                Text(
                    text = agentDetailUiState.agentDetail.displayName,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

    }
}