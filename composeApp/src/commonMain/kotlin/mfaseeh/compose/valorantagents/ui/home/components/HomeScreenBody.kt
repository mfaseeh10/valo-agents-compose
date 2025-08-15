package mfaseeh.compose.valorantagents.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentsListUiState
import mfaseeh.compose.valorantagents.ui.theme.lightGreyV
import mfaseeh.compose.valorantagents.ui.theme.redV

@Composable
internal fun HomeScreenBody(
    uiState: AgentsListUiState,
    onAgentClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val gridState = rememberLazyGridState()
    
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is AgentsListUiState.Loading -> {
                CircularProgressIndicator(
                    color = redV,
                    strokeWidth = 3.dp
                )
            }
            
            is AgentsListUiState.GetAgentsSuccess -> {
                AgentsList(
                    agents = uiState.agents,
                    onAgentClick = onAgentClick,
                    modifier = Modifier.fillMaxSize(),
                    gridState = gridState
                )
            }
            
            is AgentsListUiState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = redV,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Something went wrong",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = uiState.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = lightGreyV,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                    )
                }
            }
            
            is AgentsListUiState.NoInternetConnection -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "⚡",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No Internet Connection",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Check your connection and try again",
                        style = MaterialTheme.typography.bodyMedium,
                        color = lightGreyV,
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            is AgentsListUiState.Init -> {
                // Initial state - could show placeholder or empty state
            }
        }
    }
}
