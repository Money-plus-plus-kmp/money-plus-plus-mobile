package com.moneyplusplus.design_system.component.chart.data

import kotlinx.datetime.LocalDate

data class ChartPoint(
    val date: LocalDate,
    val value: Double
)