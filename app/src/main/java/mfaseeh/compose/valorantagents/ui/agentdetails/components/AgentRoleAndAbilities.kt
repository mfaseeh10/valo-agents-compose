package mfaseeh.compose.valorantagents.ui.agentdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel

@Composable
fun AgentRoleAndAbilities(agent: AgentUiModel) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight(1f)
    ) {
        Column {
            Spacer(modifier = Modifier.height(12.dp))
            AgentRole(agent)
            Spacer(modifier = Modifier.height(12.dp))
            AgentAbilities(agent)
        }
    }
}