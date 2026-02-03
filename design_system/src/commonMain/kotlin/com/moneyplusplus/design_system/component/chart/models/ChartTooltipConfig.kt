package com.moneyplusplus.design_system.component.chart.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.moneyplusplus.design_system.theme.theme.Theme

data class ChartTooltipConfig(
    val backgroundColor: Color,
    val textColor: Color
) {
    companion object {
        @Composable
        fun defaults(
            backgroundColor: Color = Theme.colorScheme.surface.surface,
            textColor: Color = Theme.colorScheme.title
        ): ChartTooltipConfig = ChartTooltipConfig(
            backgroundColor = backgroundColor,
            textColor = textColor
        )
    }
}
