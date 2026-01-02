package com.moneyplusplus.design_system.utils

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun MppPreview(
    modifier: Modifier = Modifier, color: Color = Theme.colorScheme.surface.surface, content: @Composable () -> Unit
) = MoneyTheme {
    Surface(modifier = modifier, color = color) {
        content()
    }
}