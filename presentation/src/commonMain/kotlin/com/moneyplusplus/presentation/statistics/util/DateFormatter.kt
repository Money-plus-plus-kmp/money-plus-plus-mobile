package com.moneyplusplus.presentation.statistics.util

import com.moneyplusplus.design_system.component.chart.data.ChartPoint


fun List<ChartPoint>.formatAsXAxisLabels(): List<String> {
    return this.map { point ->
        val monthName = point.date.month.name.take(3).lowercase()
        val capitalizedMonth = monthName.replaceFirstChar { it.titlecase() }
        "${point.date.day} $capitalizedMonth"
    }
}
