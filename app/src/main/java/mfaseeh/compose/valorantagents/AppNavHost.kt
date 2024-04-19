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
import mfaseeh.compose.valorantagents.ui.home.viewmodel.HomeViewModel
import mfaseeh.compose.valorantagents.ui.home.HomeScreen
import mfaseeh.compose.valorantagents.ui.home.AgentDetail
import mfaseeh.compose.valorantagents.ui.home.viewmodel.AgentDetailViewModel

@Composable
internal fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.HomeScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val agentsListUiState by viewModel.agentsListUiState.collectAsStateWithLifecycle()
            HomeScreen(
                navController = navController,
                agentsListUiState = agentsListUiState
            ) { uuid ->
                navController.navigate(Screen.AgentDetail.route + "/$uuid")
            }
        }
        composable(
            route = Screen.AgentDetail.route + "/{uuid}",
            arguments = listOf(
                navArgument("uuid") { type = NavType.StringType }
            )
        ) {
            val uuid = it.arguments?.getString("uuid")
            val viewModel = hiltViewModel<AgentDetailViewModel>()
            viewModel.getAgentDetails(uuid = uuid ?: "")
            val agentsDetailUiState by viewModel.agentsDetailsUiState.collectAsStateWithLifecycle()
            AgentDetail(agentsDetailUiState)
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
    object AgentDetail : Screen("agent-details")
    object HomeScreen : Screen("home-screen")
}

