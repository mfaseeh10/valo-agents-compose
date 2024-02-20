package mfaseeh.compose.valorantagents

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mfaseeh.compose.valorantagents.ui.home.HomeScreen
import mfaseeh.compose.valorantagents.ui.home.components.AgentDetail

@Composable
internal fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "home-screen"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home-screen") {
            HomeScreen(navController = navController)
        }
        composable("agent-details") {
            AgentDetail()
        }
    }

}

