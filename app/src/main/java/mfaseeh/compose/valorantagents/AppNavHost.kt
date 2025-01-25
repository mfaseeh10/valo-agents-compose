package mfaseeh.compose.valorantagents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mfaseeh.compose.valorantagents.ui.agentdetails.AgentDetailScreen
import mfaseeh.compose.valorantagents.ui.agentdetails.AgentDetailViewModel
import mfaseeh.compose.valorantagents.ui.home.HomeScreen
import mfaseeh.compose.valorantagents.ui.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

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
            val viewModel: HomeViewModel = koinViewModel()
            val agentsListUiState by viewModel.agentsListUiState.collectAsStateWithLifecycle()
            HomeScreen(
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
            val viewModel: AgentDetailViewModel = koinViewModel()
            viewModel.getAgentDetails(uuid = uuid ?: "")
            val agentsDetailUiState by viewModel.agentsDetailsUiState.collectAsStateWithLifecycle()
            AgentDetailScreen(agentsDetailUiState, navController)
        }
    }

}

sealed class Screen(val route: String) {
    object AgentDetail : Screen("agent-details")
    object HomeScreen : Screen("home-screen")
}

