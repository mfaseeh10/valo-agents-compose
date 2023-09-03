package com.tech.bazaar.baload.ui.base.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import mfaseeh.compose.valorantagents.R

data class TextStyleBuilder(
    private val fontFamily: FontFamily? = null,
    private val fontWeight: FontWeight? = null,
    private val color: Color = Color.Unspecified,
    private val fontSize: TextUnit = TextUnit.Unspecified,
    private val letterSpacing: TextUnit = TextUnit.Unspecified,
    private val lineHeight: TextUnit = TextUnit.Unspecified
) {
    fun interMedium() =
        copy(fontFamily = FontFamily(Font(R.font.intermedium)))

    fun interRegular() =
        copy(fontFamily = FontFamily(Font(R.font.interregular)))

    fun interSemiBold() =
        copy(fontFamily = FontFamily(Font(R.font.intersemibold)))

    fun playFairDisplayRegular() =
        copy(fontFamily = FontFamily(Font(R.font.playfairdisplayregular)))

    fun playfairDisplayBold() =
        copy(fontFamily = FontFamily(Font(R.font.playfair_display_bold)))

    fun fontWeight(fontWeight: FontWeight) =
        copy(fontWeight = fontWeight)

    fun color(color: Color) =
        copy(color = color)

    fun fontSize(fontSize: TextUnit) =
        copy(fontSize = fontSize)

    fun letterSpacing(letterSpacing: TextUnit) =
        copy(letterSpacing = letterSpacing)

    fun lineHeight(lineHeight: TextUnit) =
        copy(lineHeight = lineHeight)

    fun build() = TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight
    )
}
