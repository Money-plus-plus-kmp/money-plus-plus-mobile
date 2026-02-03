package com.moneyplusplus.design_system.component.chart.utils

import com.moneyplusplus.design_system.component.chart.data.ChartPoint

fun List<ChartPoint>.calculateXAxisData(): List<String> {
    return this.map {
        val monthName = it.date.month.name.take(3).lowercase()
        "${it.date.day} ${monthName.replaceFirstChar { char -> char.titlecase() }}"
    }
}
