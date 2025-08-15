package mfaseeh.compose.valorantagents.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import mfaseeh.compose.valorantagents.ui.home.components.HomeScreenBody
import mfaseeh.compose.valorantagents.ui.home.components.HomeScreenHeader
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentsListUiState
import mfaseeh.compose.valorantagents.ui.theme.*

@Composable
internal fun HomeScreen(
    uiState: AgentsListUiState,
    onAgentClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(blackV, blueV)
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreenHeader()
            
            HomeScreenBody(
                uiState = uiState,
                onAgentClick = onAgentClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}