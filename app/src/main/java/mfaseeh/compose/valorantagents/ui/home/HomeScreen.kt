package mfaseeh.compose.valorantagents.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import mfaseeh.compose.valorantagents.R
import mfaseeh.compose.valorantagents.data.model.Agent
import mfaseeh.compose.valorantagents.domain.AgentsListUiState
import mfaseeh.compose.valorantagents.domain.HomeViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val agentsListUiState by viewModel.agentsListUiState.collectAsStateWithLifecycle()

    when (agentsListUiState) {
        is AgentsListUiState.GetAgentsSuccess -> {
            AllAgentsList(agentsListUiState as AgentsListUiState.GetAgentsSuccess)
        }

        is AgentsListUiState.Loading -> {
            Text(text = "Loading")
        }

        is AgentsListUiState.Error -> {
            Text(text = "An error has occured")
        }

        else -> {
            Text(text = "No internet connection")
        }

    }
}

@Composable
private fun AllAgentsList(agentsListUiState: AgentsListUiState.GetAgentsSuccess) {
    LazyVerticalGrid(
        state = rememberLazyGridState(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        (agentsListUiState.agents).forEachIndexed { _, agent ->
            item {
                if (agent.isPlayableCharacter) {
                    AgentItem(agent)
                }
            }
        }
    }
}



@Composable
private fun AgentItem(agent: Agent) {
    ElevatedCard(
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(agent.fullPortrait)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier
                    .height(250.dp)
                    .padding(2.dp),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = agent.displayName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
fun PrevAgentCard() {
    AgentItem(
        Agent(
            uuid = "",
            displayName = "Breach",
            displayIcon = "drawable/breach.png",
            isPlayableCharacter = true,
            description = "",
        )
    )
}