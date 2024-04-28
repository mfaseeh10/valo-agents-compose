package mfaseeh.compose.valorantagents.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import mfaseeh.compose.valorantagents.R
import mfaseeh.compose.valorantagents.domain.model.AgentUiModel
import mfaseeh.compose.valorantagents.domain.model.RoleUiModel

@Composable
internal fun AgentItem(agent: AgentUiModel, onClick: (String) -> Unit = {}){
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp)
            .clickable { onClick(agent.uuid)  },
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
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(agent.fullPortrait)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
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
            role = RoleUiModel()
        )
    )
}