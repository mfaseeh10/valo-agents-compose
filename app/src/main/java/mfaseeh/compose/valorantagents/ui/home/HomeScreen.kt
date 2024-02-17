package mfaseeh.compose.valorantagents.ui.home

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import mfaseeh.compose.valorantagents.domain.AgentsListUiState
import mfaseeh.compose.valorantagents.domain.HomeViewModel
import mfaseeh.compose.valorantagents.ui.home.components.AgentItem
import mfaseeh.compose.valorantagents.ui.home.components.LoadingView
import mfaseeh.compose.valorantagents.ui.home.components.shimmerEffect
import okhttp3.internal.addHeaderLenient

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val agentsListUiState by viewModel.agentsListUiState.collectAsStateWithLifecycle()
    var isVisible by remember {
        mutableStateOf(true)
    }
    when (agentsListUiState) {
        is AgentsListUiState.GetAgentsSuccess -> {
            Column {
                //todo implement search bar
//                Column(
//                    modifier = modifier
//                        .padding()
//                        .width(100.dp)
//                        .height(100.dp)
//                ) {
//                    Text(text = "All Agents", modifier = Modifier.padding(8.dp))
//                    Text(text = "Search your agent")
//                }
                AllAgentsList(agentsListUiState as AgentsListUiState.GetAgentsSuccess, modifier)
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


@Composable
internal fun AllAgentsList(
    agentsListUiState: AgentsListUiState.GetAgentsSuccess,
    modifier: Modifier,
    onScrolled: () -> Unit = {}
) {
    val gridState = rememberLazyGridState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = gridState) {
        val index by derivedStateOf { gridState.isScrollInProgress }
        snapshotFlow { index }
            .collect {
                if (it) {
                    Log.d("Scroll", "scrolled")
                } else {
                    Log.d("Scroll", "not scrolled")
                }

            }
    }
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        modifier = modifier
            .fillMaxSize()
            .padding()
            .scrollable(scrollState, orientation = Orientation.Vertical),
        userScrollEnabled = true

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