package mfaseeh.compose.valorantagents.ui.home.components

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentsListUiState

@Composable
internal fun AllAgentsList(
    agentsListUiState: AgentsListUiState.GetAgentsSuccess,
    modifier: Modifier,
    onClick: (String) -> Unit,
    onScrolled: (Boolean) -> Unit = {}
) {
    val gridState = rememberLazyGridState()

    LaunchedEffect(key1 = gridState) {
        val index by derivedStateOf { gridState.firstVisibleItemIndex }
        snapshotFlow { index }
            .collect {
                Log.d("Scroll", "$it")
                if(it > 0)
                    onScrolled(false)
                else
                    onScrolled(true)
            }
    }

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        (agentsListUiState.agents).forEachIndexed { _, agent ->
            item {
                if (agent.isPlayableCharacter) {
                    AgentItem(agent, onClick =  onClick)
                }
            }
        }
    }
}