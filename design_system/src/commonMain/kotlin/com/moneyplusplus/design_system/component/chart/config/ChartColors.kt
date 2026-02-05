package com.moneyplusplus.design_system.component.chart.config

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.moneyplusplus.design_system.theme.theme.Theme

data class ChartColors(
    val lineColor: Color,
    val gridColor: Color,
) {
    companion object {
        @Composable
        fun defaults(
            lineColor: Color = Theme.colorScheme.primary.primary,
            gridColor: Color = Theme.colorScheme.stroke,
        ): ChartColors = ChartColors(
            lineColor = lineColor,
            gridColor = gridColor,
        )
    }
}
