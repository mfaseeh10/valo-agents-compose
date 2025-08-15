package mfaseeh.compose.valorantagents.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentsListUiState

@Composable
internal fun HomeScreenBody(
    modifier: Modifier = Modifier,
    agentsListUiState: AgentsListUiState,
    onClick: (String) -> Unit
) {

    var isVisible by remember {
        mutableStateOf(true)
    }
    when (agentsListUiState) {
        is AgentsListUiState.GetAgentsSuccess -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                HomeScreenHeader(isVisible, modifier)
                AllAgentsList(
                    agentsListUiState = agentsListUiState,
                    modifier = modifier,
                    onClick = onClick
                ) {
                    isVisible = it
                }
            }
        }

        is AgentsListUiState.Loading -> {
            LoadingView()
        }

        is AgentsListUiState.Error -> {
            //Todo implement later
            Text(text = "An error has occured")
        }

        else -> {
            Text(text = "No internet connection")
        }

    }
}
