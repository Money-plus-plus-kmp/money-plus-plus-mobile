package com.moneyplusplus.design_system.chart.models

import androidx.compose.runtime.Composable

data class ChartConfig(
    val dimensions: ChartDimensions,
    val colors: ChartColors,
    val styles: ChartStyles,
    val animationEnabled: Boolean = true,
    val yAxisRange: Int = 6
) {
    companion object {
        @Composable
        fun defaults(
            dimensions: ChartDimensions = ChartDimensions.Default,
            colors: ChartColors = ChartColors.defaults(),
            styles: ChartStyles = ChartStyles.defaults(),
            animationEnabled: Boolean = true,
            yAxisRange: Int = 6
        ): ChartConfig = ChartConfig(
            dimensions = dimensions,
            colors = colors,
            styles = styles,
            animationEnabled = animationEnabled,
            yAxisRange = yAxisRange
        )
    }
}