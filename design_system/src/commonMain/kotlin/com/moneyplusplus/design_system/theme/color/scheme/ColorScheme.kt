package com.moneyplusplus.design_system.theme.color.scheme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val primary: Primary,
    val secondary: Secondary,
    val title: Color,
    val body: Color,
    val hint: Color,
    val stroke: Color,
    val surface: Surface,
    val onPrimary: OnPrimary,
    val disable: Color,
    val accent: Accent
) {

    data class Primary(
        val primary: Color,
        val variant: Color
    )

    data class Secondary(
        val secondary: Color,
        val variant: Color
    )

    data class Surface(
        val surface: Color,
        val surfaceLow: Color,
        val surfaceHigh: Color
    )

    data class OnPrimary(
        val onPrimary: Color,
        val onPrimaryBody: Color,
        val onPrimaryStroke: Color
    )

    data class Accent(
        val red: Red,
        val yellow: Yellow,
        val green: Green
    )

    data class Red(
        val red: Color,
        val redVariant: Color
    )

    data class Yellow(
        val yellow: Color,
        val yellowVariant: Color
    )

    data class Green(
        val green: Color,
        val greenVariant: Color
    )
}

internal val LocalColorScheme = staticCompositionLocalOf { LightColorScheme }