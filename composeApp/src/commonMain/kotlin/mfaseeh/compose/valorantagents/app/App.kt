package mfaseeh.compose.valorantagents.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import mfaseeh.compose.valorantagents.ui.navigation.AppNavHost
import mfaseeh.compose.valorantagents.ui.theme.ValorantAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ValorantAppTheme {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}
