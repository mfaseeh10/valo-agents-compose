package mfaseeh.compose.valorantagents.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.tech.bazaar.baload.ui.base.theme.TextStyleBuilder
import mfaseeh.compose.valorantagents.R


@Composable
internal fun NoRequestView() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_no_request),
//            contentDescription = "",
//            modifier = Modifier.size(113.dp, 129.dp)
//        )
        Text(
            text = stringResource(id = R.string.empty_response_text),
            style = TextStyleBuilder()
                .interRegular()
                .fontWeight(FontWeight.Normal)
                .fontSize(14.sp)
                .color(Color.Gray)
                .build()
        )
    }
}


@Composable
@Preview
internal fun NoRequestViewPreview() {
    NoRequestView()
}
