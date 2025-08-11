package mfaseeh.compose.valorantagents.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import mfaseeh.compose.valorantagents.ui.home.components.HomeScreenBody
import mfaseeh.compose.valorantagents.ui.home.uistates.AgentsListUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    agentsListUiState: AgentsListUiState,
    onClick: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.background
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Valorant Agents",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                    )
                }
            }
        }
    ) { paddingValues ->
        //TODO format list themeing
//        previewStaggeredList(paddingValues)
        HomeScreenBody(
            modifier = Modifier.padding(paddingValues),
            agentsListUiState = agentsListUiState,
            onClick = onClick
        )
    }
}

@Preview
@Composable
fun PreviewStaggeredList(paddingValues: PaddingValues = PaddingValues()) {

    val configuration = LocalConfiguration.current
    val height = configuration.screenHeightDp * 0.5

    val redHeight  = height * 0.3
    val blueHeight  = height * 0.25
    val greenHeight  = height * 0.25
    val magentaHeight  = height * 0.2

    val items = listOf(
        GridItem("1", color =  Color.Red, width =  0.dp, height =  redHeight.dp),
        GridItem("2", color =  Color.Blue, width =  0.dp, height = blueHeight.dp),
        GridItem("3", color = Color.Green, width = 0.dp, height = greenHeight.dp),
        GridItem("3", color = Color.Magenta, width = 0.dp, height = magentaHeight.dp),
    )


    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(paddingValues)
            .height(height.dp)
            .background(Color.White)

    ){
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                gridItem(item = item)
            }
        }
        Box {
            Text(text = height.toString(), fontSize = 32.sp, modifier = Modifier.align(Alignment.BottomCenter))
        }
    }

}

@Composable
fun gridItem(
    modifier: Modifier = Modifier,
    item: GridItem
) {
    Box(
        modifier = modifier
            .background(item.color)
            .height(item.height)
            .height(item.width)
    )

}

data class GridItem(
    val id: String,
    val color: Color,
    val width: Dp,
    val height: Dp
)

