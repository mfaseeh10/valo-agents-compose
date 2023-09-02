package mfaseeh.compose.valorantagents

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (agentsListUiState is AgentsListUiState.GetAgentsSuccess) {
                        LazyColumn(
                            state = rememberLazyListState(),
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val data = (agentsListUiState as AgentsListUiState.GetAgentsSuccess).agents
                            (data)?.forEachIndexed { index, agent ->
                                item {
                                    Text(text = agent.displayName)
                                }
                            }
                        }
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