package mfaseeh.compose.valorantagents.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentDetailUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AgentDetailScreen(
    agentDetailUiState: AgentDetailUiState,
    navHostController: NavHostController
) {
    when (agentDetailUiState) {
        is AgentDetailUiState.Error -> {
            TODO()
        }

        is AgentDetailUiState.Init -> {
            TODO()
        }

        is AgentDetailUiState.Loading -> {
//            Text(text = "Loading")
        }

        is AgentDetailUiState.Success -> {
            val pagerState = rememberPagerState(initialPage = 0)
            VerticalPager(state = pagerState, pageCount = 2) { page ->

                when (page) {
                    0 -> {
                        AgentHeader(agentDetailUiState, navHostController)
                    }

                    1 -> {
                        AgentRoleAndAbilities(agentDetailUiState.agentDetail)
                    }
                }
            }

        }
    }
}

@Composable
private fun AgentRoleAndAbilities(agent: AgentUiModel) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight(1f)
    ) {
        Column {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "//Role",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = agent.role.displayName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                AsyncImage(
                    model = agent.role.displayIcon,
                    contentDescription = "Role Icon",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.height(30.dp).width(30.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = agent.role.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "//Abilities",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }
    }
}

@Composable
private fun AgentHeader(
    agentDetailUiState: AgentDetailUiState.Success,
    navHostController: NavHostController
) {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        AgentDetailBody(agent = agentDetailUiState.agentDetail)
        IconButton(
            onClick = { navHostController.popBackStack() },
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Icon(
                Icons.Sharp.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier
                    .size(50.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun AgentDetailBody(agent: AgentUiModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            AsyncImage(
                model = agent.background,
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.65f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            AsyncImage(
                model = agent.fullPortraitV2,
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
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "//Background",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = agent.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

