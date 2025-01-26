
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun Loader(
    visible: Boolean,
    modifier: Modifier
) {
    if (visible) Box(
        modifier = modifier
    ) {
        CircularProgressIndicator(
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
