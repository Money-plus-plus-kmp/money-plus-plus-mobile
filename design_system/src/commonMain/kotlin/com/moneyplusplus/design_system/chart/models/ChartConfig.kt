package com.moneyplusplus.design_system.chart.models

data class ChartConfig(
    val dimensions: ChartDimensions = ChartDimensions.Default,
    val animationEnabled: Boolean = true,
) {
    companion object {
        val Default = ChartConfig()
    }
}