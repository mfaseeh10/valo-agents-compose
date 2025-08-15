package mfaseeh.compose.valorantagents.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import mfaseeh.compose.valorantagents.domain.model.AbilityUiModel
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.domain.model.RoleUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun AgentItem(agent: AgentUiModel, onClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp)
            .clickable { onClick(agent.uuid) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 4.dp)
        ) {
            AsyncImage(
                model = agent.fullPortrait.ifEmpty { agent.displayIcon },
                contentDescription = agent.displayName,
                modifier = Modifier
                    .height(250.dp)
                    .padding(2.dp),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = agent.displayName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview
@Composable
fun PrevAgentCard() {
    AgentItem(
        AgentUiModel(
            uuid = "",
            displayName = "Breach",
            displayIcon = "drawable/breach.png",
            isPlayableCharacter = true,
            description = "",
            fullPortrait = "",
            fullPortraitV2 = "",
            background = "",
            role = RoleUiModel(),
            abilities = listOf(AbilityUiModel())
        )
    )
}
