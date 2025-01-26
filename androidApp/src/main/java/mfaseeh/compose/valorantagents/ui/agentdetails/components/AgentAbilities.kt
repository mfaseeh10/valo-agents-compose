package mfaseeh.compose.valorantagents.ui.agentdetails.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.ui.home.components.ExpandableCard

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun AgentAbilities(agent: AgentUiModel) {
    Text(
        text = "//Abilities",
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(12.dp))
    LazyColumn {
        items(agent.abilities.size) { index ->
            ExpandableCard(
                imageUrl = agent.abilities[index].displayIcon,
                title = agent.abilities[index].displayName,
                description = agent.abilities[index].description
            )
        }
    }
}