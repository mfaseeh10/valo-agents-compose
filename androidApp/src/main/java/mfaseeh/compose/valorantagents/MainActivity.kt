package mfaseeh.compose.valorantagents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import mfaseeh.compose.valorantagents.ui.theme.ValorantAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}
