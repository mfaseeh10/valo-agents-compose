package mfaseeh.compose.valorantagents

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surface),
                        topBar = {
                            TopAppBar (
                                backgroundColor = MaterialTheme.colorScheme.surface
                            ) {

                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Valorant Agents",
                                        style = MaterialTheme.typography.titleMedium.
                                        copy(color = MaterialTheme.colorScheme.onSurface ),
                                    )

                                }
                            }
                        }
                    ) {
                        paddingValues ->
                        HomeScreen(modifier = Modifier.padding(paddingValues))
                    }
                }
            }
        }
    }
}
