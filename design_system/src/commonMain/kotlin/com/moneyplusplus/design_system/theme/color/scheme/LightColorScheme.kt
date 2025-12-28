package com.moneyplusplus.design_system.theme.color.scheme

import androidx.compose.ui.graphics.Color

val LightColorScheme = ColorScheme(
    primary = ColorScheme.Primary(
        primary = Color(0xFFDC143C),
        variant = Color(0xFFFEF1F4)
    ),
    secondary = ColorScheme.Secondary(
        secondary = Color(0xFF0496AD),
        variant = Color(0xFFEAF3F4)
    ),
    title = Color(0xDE1F1F1E),
    body = Color(0xA81F1F1E),
    hint = Color(0x661F1F1E),
    stroke = Color(0x1A1F1F1E),
    surface = ColorScheme.Surface(
        surface = Color(0xFFF8F8F8),
        surfaceLow = Color(0xFFFFFFFF),
        surfaceHigh = Color(0xFFF2F2F2)
    ),
    onPrimary = ColorScheme.OnPrimary(
        onPrimary = Color(0xDEFFFFFF),
        onPrimaryBody = Color(0xA8EEEEEE),
        onPrimaryStroke = Color(0x29FFFFFF)
    ),
    disable = Color(0xFFDDE1E4),
    accent = ColorScheme.Accent(
        red = ColorScheme.Red(
            red = Color(0xFFE54F40),
            redVariant = Color(0xFFFEEDEC)
        ),
        yellow = ColorScheme.Yellow(
            yellow = Color(0xFFF5A623),
            yellowVariant = Color(0xFFFEF3E1)
        ),
        green = ColorScheme.Green(
            green = Color(0xFF51AC46),
            greenVariant = Color(0xFFF1F9F1)
        )
    )
)