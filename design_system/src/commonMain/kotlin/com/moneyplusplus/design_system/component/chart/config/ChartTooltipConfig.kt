package com.moneyplusplus.design_system.component.chart.config

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.moneyplusplus.design_system.theme.theme.Theme

data class ChartTooltipConfig(
    val backgroundColor: Color,
    val textStyle: TextStyle
) {
    companion object {
        @Composable
        fun defaults(
            backgroundColor: Color = Theme.colorScheme.surface.surface,
            textStyle: TextStyle = Theme.typography.label.xSmall.copy(color = Theme.colorScheme.title)
        ): ChartTooltipConfig = ChartTooltipConfig(
            backgroundColor = backgroundColor,
            textStyle = textStyle
        )
    }
}
