package mfaseeh.compose.valorantagents.ui.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose.greyV
import com.example.compose.lightGreyV

//todo add theming for agent abilities card
@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    imageUrl: String,
    title: String,
    titleFontStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    description: String,
    descriptionFontStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    padding: Dp = 15.dp
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = if (isSystemInDarkTheme()) greyV else lightGreyV,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "card image",
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    )
                Text(
                    text = title,
                    style = titleFontStyle,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Drop Down",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            if (expandedState) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = description,
                    style = descriptionFontStyle,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview("Expandable Card")
@Composable
fun ExpandableCardPreview() {
    ExpandableCard(
        imageUrl = "",
        title = "Expandable Card",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing. Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    )
}