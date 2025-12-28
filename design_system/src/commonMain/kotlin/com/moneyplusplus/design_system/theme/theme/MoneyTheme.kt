package com.moneyplusplus.design_system.theme.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.moneyplusplus.design_system.theme.color.scheme.LocalColorScheme
import com.moneyplusplus.design_system.theme.color.scheme.LightColorScheme
import com.moneyplusplus.design_system.theme.typography.createAppTypography
import com.moneyplusplus.design_system.theme.typography.LocalTypography

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