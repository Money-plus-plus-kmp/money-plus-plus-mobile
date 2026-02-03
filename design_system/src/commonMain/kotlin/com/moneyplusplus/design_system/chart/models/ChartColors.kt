package com.moneyplusplus.design_system.chart.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.moneyplusplus.design_system.theme.theme.Theme

data class ChartColors(
    val lineColor: Color,
    val gridColor: Color,
    val axisLabelColor: Color,
    val tooltipBackground: Color,
    val tooltipTextColor: Color
) {
    companion object {
        @Composable
        fun defaults(
            lineColor: Color = Theme.colorScheme.primary.primary,
            gridColor: Color = Theme.colorScheme.stroke,
            axisLabelColor: Color = Theme.colorScheme.hint,
            tooltipBackground: Color = Theme.colorScheme.surface.surface,
            tooltipTextColor: Color = Theme.colorScheme.title
        ): ChartColors = ChartColors(
            lineColor = lineColor,
            gridColor = gridColor,
            axisLabelColor = axisLabelColor,
            tooltipBackground = tooltipBackground,
            tooltipTextColor = tooltipTextColor
        )
    }
}
