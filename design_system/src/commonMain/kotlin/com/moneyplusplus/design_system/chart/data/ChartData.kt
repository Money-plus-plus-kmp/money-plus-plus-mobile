package com.moneyplusplus.design_system.chart.data

/**
 * Container for chart data with computed properties
 */
data class ChartData(
    val points: List<ChartPoint>,
    val title: String = "",
    val valuePrefix: String = "",
    val valueSuffix: String = ""
) {
    val minValue: Double
        get() = points.minOfOrNull { it.value } ?: 0.0

    val maxValue: Double
        get() = points.maxOfOrNull { it.value } ?: 0.0

    val valueRange: Double
        get() = maxValue - minValue

    val isEmpty: Boolean
        get() = points.isEmpty()
}