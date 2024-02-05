package mfaseeh.compose.valorantagents.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import mfaseeh.compose.valorantagents.R
import mfaseeh.compose.valorantagents.domain.AgentsListUiState
import mfaseeh.compose.valorantagents.domain.HomeViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val agentsListUiState by viewModel.agentsListUiState.collectAsStateWithLifecycle()
    AllAgentsList(agentsListUiState = agentsListUiState)

}

@Composable
private fun AllAgentsList(agentsListUiState: AgentsListUiState) {
    when (agentsListUiState) {
        is AgentsListUiState.GetAgentsSuccess -> {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                (agentsListUiState.agents).forEachIndexed { _, agent ->
                    item {
                        if (agent.isPlayableCharacter) {
                            Row() {
                                AsyncImage(
                                    model = ImageRequest.Builder(context = LocalContext.current)
                                        .data(agent.displayIcon)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    error = painterResource(R.drawable.ic_broken_image),
                                    placeholder = painterResource(R.drawable.loading_img),

                                    )
                                Text(text = agent.displayName)
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    }
                }
            }
        }

        is AgentsListUiState.Error -> {
            Text(text = "An error has occured")
        }

        else -> {
            Text(text = "No response")
        }
    }
}