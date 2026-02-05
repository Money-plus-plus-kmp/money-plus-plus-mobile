package com.moneyplusplus.design_system.component.chart.config

import androidx.compose.runtime.Composable

/**
 * Main configuration object for the chart.
 * Combines colors, styles, tooltip, and behavior settings.
 */
data class ChartConfig(
    /** Color configuration */
    val colors: ChartColors,
    /** Text style configuration */
    val styles: ChartStyles,
    /** Tooltip configuration */
    val tooltip: ChartTooltipConfig,
    /** Whether to animate the line drawing */
    val animationEnabled: Boolean = true,
    /** Number of intervals on the Y-axis */
    val yAxisRange: Int = 6
) {
    companion object {
        /**
         * Creates a default chart configuration.
         */
        @Composable
        fun defaults(
            colors: ChartColors = ChartColors.defaults(),
            styles: ChartStyles = ChartStyles.defaults(),
            tooltip: ChartTooltipConfig = ChartTooltipConfig.defaults(),
            animationEnabled: Boolean = true,
            yAxisRange: Int = 6
        ): ChartConfig = ChartConfig(
            colors = colors,
            styles = styles,
            tooltip = tooltip,
            animationEnabled = animationEnabled,
            yAxisRange = yAxisRange
        )
    }
}
