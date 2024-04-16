package mfaseeh.compose.valorantagents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import mfaseeh.compose.valorantagents.ui.home.viewmodel.HomeViewModel
import mfaseeh.compose.valorantagents.ui.home.HomeScreen
import mfaseeh.compose.valorantagents.ui.home.components.AgentDetail

@Composable
internal fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(startDestination = Screen.Home.HomeScreen.route, route = Screen.Home.route) {
            composable(Screen.Home.HomeScreen.route) {
                val viewModel = it.sharedViewModel<HomeViewModel>(navController = navController)
                val agentsListUiState by viewModel.agentsListUiState.collectAsStateWithLifecycle()
                HomeScreen(
                    navController = navController,
                    agentsListUiState = agentsListUiState
                ) { uuid ->
                    navController.navigate(Screen.Home.AgentDetail.route + "/$uuid")
                }
            }
            composable(
                route = Screen.Home.AgentDetail.route + "/{uuid}",
                arguments = listOf(
                    navArgument("uuid") { type = NavType.StringType }
                )
            ) {
                val uuid = it.arguments?.getString("uuid")
                val viewModel = it.sharedViewModel<HomeViewModel>(navController = navController)
                viewModel.getAgentDetails(uuid = uuid ?: "")
                val agentsDetailUiState by viewModel.agentsDetailsUiState.collectAsStateWithLifecycle()
                AgentDetail(agentsDetailUiState)
            }
        }
    }

}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

sealed class Screen(val route: String) {
    object Home : Screen("home") {
        object AgentDetail : Screen("agent-details")
        object HomeScreen : Screen("home-screen")
    }

}

