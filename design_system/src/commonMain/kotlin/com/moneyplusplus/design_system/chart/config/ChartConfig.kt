package com.moneyplusplus.design_system.chart.config

/**
 * Main configuration combining all chart settings
 */
data class ChartConfig(
    val colors: ChartColors = ChartColors.Default,
    val dimensions: ChartDimensions = ChartDimensions.Default,
    val tooltip: TooltipConfig = TooltipConfig.Default,
    val showGrid: Boolean = true,
    val showYAxisLabels: Boolean = true,
    val showXAxisLabels: Boolean = true,
    val smoothLine: Boolean = true,
    val showGradient: Boolean = true,
    val yAxisLabelCount: Int = 5,
    val animationEnabled: Boolean = true,
    val animationDuration: Int = 1000
) {
    companion object {
        val Default = ChartConfig()
    }
}