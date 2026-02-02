package com.moneyplusplus.design_system.chart.data

import kotlinx.datetime.LocalDate

data class ChartPoint(
    val date: LocalDate,
    val value: Double
)