package com.moneyplusplus.design_system.chart.models

import androidx.compose.runtime.Composable

data class ChartConfig(
    val colors: ChartColors,
    val styles: ChartStyles,
    val tooltip: ChartTooltipConfig,
    val animationEnabled: Boolean = true,
    val yAxisRange: Int = 6
) {
    companion object {
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