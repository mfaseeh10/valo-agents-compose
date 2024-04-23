package mfaseeh.compose.valorantagents.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mfaseeh.compose.valorantagents.R
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentDetailUiState

@Composable
internal fun AgentDetailScreen(agentDetailUiState: AgentDetailUiState) {
    when (agentDetailUiState) {
        is AgentDetailUiState.Error -> {
            TODO()
        }

        is AgentDetailUiState.Init -> {
            TODO()
        }

        is AgentDetailUiState.Loading -> {
            Text(text = "Loading")
        }

        is AgentDetailUiState.Success -> {
            AgentDetailBody(agent = agentDetailUiState.agentDetail)
        }
    }
}

@Composable
fun AgentDetailBody(agent: AgentUiModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            AsyncImage(
                model = agent.background,
                placeholder = painterResource(id = R.drawable.background_jett),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.65f)
            )
            AsyncImage(
                model = agent.fullPortraitV2,
                placeholder = painterResource(id = R.drawable.fullportrait_jett),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.7f)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = agent.displayName,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = agent.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
