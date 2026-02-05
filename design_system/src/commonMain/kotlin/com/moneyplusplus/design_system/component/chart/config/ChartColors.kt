package com.moneyplusplus.design_system.component.chart.config

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.moneyplusplus.design_system.theme.theme.Theme

/**
 * Color configuration for the chart.
 */
data class ChartColors(
    /** Color of the main chart line */
    val lineColor: Color,
    /** Color of the grid lines */
    val gridColor: Color,
    /** Color of the axis labels */
    val axisLabelColor: Color
) {
    companion object {
        /**
         * Creates default chart colors based on the current theme.
         */
        @Composable
        fun defaults(
            lineColor: Color = Theme.colorScheme.primary.primary,
            gridColor: Color = Theme.colorScheme.stroke,
            axisLabelColor: Color = Theme.colorScheme.hint
        ): ChartColors = ChartColors(
            lineColor = lineColor,
            gridColor = gridColor,
            axisLabelColor = axisLabelColor
        )
    }
}
