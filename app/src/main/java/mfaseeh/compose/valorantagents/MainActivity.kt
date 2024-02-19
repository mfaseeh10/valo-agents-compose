package mfaseeh.compose.valorantagents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mfaseeh.compose.valorantagents.ui.home.HomeScreen
import mfaseeh.compose.valorantagents.ui.theme.ValorantAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home-screen") {
                    composable("home-screen") {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Scaffold(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.surface),
                                topBar = {
                                    TopAppBar(
                                        backgroundColor = MaterialTheme.colorScheme.surface
                                    ) {

                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Text(
                                                text = "Valorant Agents",
                                                style = MaterialTheme.typography.titleMedium.copy(
                                                    color = MaterialTheme.colorScheme.onSurface
                                                ),
                                            )

                                        }
                                    }
                                }
                            ) { paddingValues ->
                                HomeScreen(
                                    modifier = Modifier.padding(paddingValues),
                                    onClick = {
                                        navController.navigate("agent-details")
                                    }
                                )

                            }
                        }

                    }
                    composable("agent-details") {
                        AgentDetail()
                    }
                }
            }
        }
    }

    @Composable
    private fun AgentDetail() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Text(
                text = "Agent Detail",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
