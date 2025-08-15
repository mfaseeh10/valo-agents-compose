package mfaseeh.compose.valorantagents.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import mfaseeh.compose.valorantagents.ui.theme.ValorantAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeScreenHeader(
    isVisible: Boolean,
    modifier: Modifier
) {
    AnimatedVisibility(
        visible = isVisible
    ) {
        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    color = MaterialTheme.colorScheme.onSurface
                )
            ) {
                append("Get To Know Your\nFavorite")
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(" Agents")
            }
        }
        Column() {
            Box(
                modifier = modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .wrapContentSize()
            ) {
                Text(
                    text = annotatedString,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize
                )
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

@Preview
@Composable
fun PrevHeader() {
    ValorantAppTheme {
        Surface {
            HomeScreenHeader(true, Modifier.wrapContentSize())
        }
    }
}
