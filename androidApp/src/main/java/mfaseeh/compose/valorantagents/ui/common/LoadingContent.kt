
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun LoadingContent(
    loading: Boolean,
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else if (loading) {
        Loader(
            visible = true,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    } else {
        content.invoke()
    }
}
