package mfaseeh.compose.valorantagents.ui.agentdetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel

@Composable
fun AgentRole(agent: AgentUiModel) {
    Text(
        text = "//Role",
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = agent.role.displayName,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        AsyncImage(
            model = agent.role.displayIcon,
            contentDescription = "Role Icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = agent.role.description,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}