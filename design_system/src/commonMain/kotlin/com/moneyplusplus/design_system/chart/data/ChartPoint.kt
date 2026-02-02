package com.moneyplusplus.design_system.chart.data

import kotlinx.datetime.LocalDate

/**
 * Represents a single data point on the chart
 */
data class ChartPoint(
    val date: LocalDate,
    val value: Double
)