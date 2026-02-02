package com.moneyplusplus.design_system.chart.config

import com.moneyplusplus.design_system.chart.models.TooltipConfig

/**
 * Main configuration combining all chart settings
 */
data class ChartConfig(
    // colors removed
    val dimensions: ChartDimensions = ChartDimensions.Default,
    val tooltip: TooltipConfig = TooltipConfig(enabled = true),
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