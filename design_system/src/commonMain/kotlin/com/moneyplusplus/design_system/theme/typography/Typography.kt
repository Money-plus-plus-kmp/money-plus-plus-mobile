package com.moneyplusplus.design_system.theme.typography

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class Typography(
    val heading: Heading,
    val title: Title,
    val body: Body,
    val label: Label
) {
    data class Heading(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )
    data class Title(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )
    data class Body(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )
    data class Label(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle,
        val xSmall: TextStyle
    )
}

val LocalTypography = staticCompositionLocalOf<Typography> { error("No Typography provided") }