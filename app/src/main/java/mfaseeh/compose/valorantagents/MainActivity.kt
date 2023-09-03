package mfaseeh.compose.valorantagents

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dagger.hilt.android.AndroidEntryPoint
import mfaseeh.compose.valorantagents.domain.AgentsListUiState
import mfaseeh.compose.valorantagents.domain.HomeViewModel
import mfaseeh.compose.valorantagents.ui.theme.ValorantAgentsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantAgentsTheme {
                val viewModel = hiltViewModel<HomeViewModel>()
                // A surface container using the 'background' color from the theme
                val agentsListUiState by viewModel.agentsListUiState.collectAsStateWithLifecycle()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,

                ) {
                    if (agentsListUiState is AgentsListUiState.GetAgentsSuccess) {
                        LazyColumn(
                            state = rememberLazyListState(),
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val data =
                                (agentsListUiState as AgentsListUiState.GetAgentsSuccess).agents
                            (data).forEachIndexed { _, agent ->
                                item {
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
                    else if(agentsListUiState is AgentsListUiState.Error){
                        Text(text = "An error has occured")
                    }
                    else {
                        Text(text = "No response")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ValorantAgentsTheme {
        Greeting("Android")
    }
}