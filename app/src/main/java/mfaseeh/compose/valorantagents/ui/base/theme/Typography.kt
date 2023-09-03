package mfaseeh.compose.valorantagents.ui.base.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tech.bazaar.baload.ui.base.theme.interFontFamily
import com.tech.bazaar.baload.ui.base.theme.playfairFontFamily
import mfaseeh.compose.valorantagents.R
val valoAgentsTypography = Typography(
    h1 = TextStyle(
        fontFamily = playfairFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ), // --> Toolbar text H1
    subtitle1 = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp
    ),

    // todo: to be updated
    h3 = TextStyle(
        fontFamily = playfairFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
    ),
    h4 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.intermedium, FontWeight.W500)
        ),
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        letterSpacing = 2.14.sp
    ),
    h5 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.interregular, FontWeight.W400)
        ),
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    h6 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.interregular, FontWeight.W400)
        ),
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.interregular, FontWeight.W400)
        ),
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        letterSpacing = 0.16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 10.sp,
    ),
    body1 = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.intermedium, FontWeight.W600)
        ),
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        letterSpacing = 1.2.sp,
        color = Color.White,
    ),
    caption = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)
