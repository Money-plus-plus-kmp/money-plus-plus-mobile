package com.moneyplusplus.design_system.theme.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import com.moneyplusplus.design_system.theme.color.scheme.ColorScheme
import com.moneyplusplus.design_system.theme.color.scheme.LightColorScheme
import com.moneyplusplus.design_system.theme.color.scheme.LocalColorScheme
import com.moneyplusplus.design_system.theme.typography.LocalTypography
import com.moneyplusplus.design_system.theme.typography.Typography
import com.moneyplusplus.design_system.theme.typography.createAppTypography

@Composable
fun MoneyTheme(
    isSystemInDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = remember(isSystemInDarkTheme) { LightColorScheme }
    val typography = createAppTypography()
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography
    ) {
        content()
    }
}

object Theme {
    val colorScheme: ColorScheme
        @Composable @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable @ReadOnlyComposable
        get() = LocalTypography.current
}