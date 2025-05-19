package mfaseeh.compose.valorantagents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import mfaseeh.compose.valorantagents.ui.agentdetails.AgentDetailScreen
import mfaseeh.compose.valorantagents.ui.agentdetails.AgentDetailViewModel
import mfaseeh.compose.valorantagents.ui.home.HomeScreen
import mfaseeh.compose.valorantagents.ui.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Screen = Screen.HomeScreen
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.HomeScreen> {
            val viewModel: HomeViewModel = koinViewModel()
            val uiState by viewModel.agentsListUiState.collectAsStateWithLifecycle()
            HomeScreen(uiState) { uuid ->
                navController.navigate(Screen.AgentDetail(uuid))
            }
        }
        composable<Screen.AgentDetail> { backStackEntry: NavBackStackEntry ->
            val route = backStackEntry.toRoute<Screen.AgentDetail>()
            val viewModel: AgentDetailViewModel = koinViewModel()
            viewModel.getAgentDetails(route.uuid)
            val uiState by viewModel.agentsDetailsUiState.collectAsStateWithLifecycle()
            AgentDetailScreen(uiState, navController)
        }
    }

}


@Serializable
sealed interface Screen {
    @Serializable
    data object HomeScreen : Screen

    @Serializable
    data class AgentDetail(val uuid: String) : Screen
}

