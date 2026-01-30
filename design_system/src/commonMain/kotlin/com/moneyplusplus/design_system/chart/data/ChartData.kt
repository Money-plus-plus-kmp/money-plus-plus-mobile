package com.moneyplusplus.design_system.chart.data

data class ChartData(
    val points: List<ChartPoint>,
    val title: String? = null
) {

    val isValid: Boolean
        get() = points.size >= 2
}