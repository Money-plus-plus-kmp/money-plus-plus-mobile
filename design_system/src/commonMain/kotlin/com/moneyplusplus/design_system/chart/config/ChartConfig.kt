package com.moneyplusplus.design_system.chart.config

import com.moneyplusplus.design_system.chart.models.TooltipConfig

data class ChartConfig(
    val dimensions: ChartDimensions = ChartDimensions.Default,
    val tooltip: TooltipConfig = TooltipConfig(enabled = true),
    val animationEnabled: Boolean = true,
) {
    companion object {
        val Default = ChartConfig()
    }
}