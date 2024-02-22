package mfaseeh.compose.valorantagents.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mfaseeh.compose.valorantagents.domain.AgentsListUiState
import mfaseeh.compose.valorantagents.domain.HomeViewModel
import mfaseeh.compose.valorantagents.ui.theme.ValorantAppTheme

@Composable
internal fun HomeScreenBody(
    modifier: Modifier = Modifier,
  agentsListUiState: AgentsListUiState,
    onClick: () -> Unit
) {

    var isVisible by remember {
        mutableStateOf(true)
    }
    when (agentsListUiState) {
        is AgentsListUiState.GetAgentsSuccess -> {
            Column (
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                HomeScreenHeader(isVisible, modifier)
                AllAgentsList(
                    agentsListUiState as AgentsListUiState.GetAgentsSuccess,
                    modifier,
                    onClick = onClick
                ) {
                    isVisible = it
                }
            }
        }

        is AgentsListUiState.Loading -> {
            LoadingView()
        }

        is AgentsListUiState.Error -> {
            //Todo implement later
            Text(text = "An error has occured")
        }

        else -> {
            Text(text = "No internet connection")
        }

    }
}

@Composable
private fun HomeScreenHeader(
    isVisible: Boolean,
    modifier: Modifier
) {
    AnimatedVisibility(
        visible = isVisible
    ) {
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(fontFamily = MaterialTheme.typography.titleMedium.fontFamily, color = MaterialTheme.colorScheme.onSurface)) {
                append("Get To Know Your\nFavorite")
            }
            withStyle(style = SpanStyle(fontFamily = MaterialTheme.typography.titleMedium.fontFamily, color = MaterialTheme.colorScheme.primary)) {
                append(" Agents")
            }
        }
        Column() {
            Box(
                modifier = modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .wrapContentSize()
            ) {
                Text(text = annotatedString, fontSize = MaterialTheme.typography.displaySmall.fontSize)
            }
            //Todo implement search
//          //  Box(
//          //      modifier = Modifier
//          //          .padding(start = 12.dp)
//          //          .wrapContentSize()
//          //  ) {
//          //      Text(text = "Search")
//          //   }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevHeader(){
   ValorantAppTheme {
        Surface {
            HomeScreenHeader(true, Modifier.wrapContentSize())
        }
   }
}


