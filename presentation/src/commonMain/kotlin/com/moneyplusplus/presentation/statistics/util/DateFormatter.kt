package com.moneyplusplus.presentation.statistics.util

import com.moneyplusplus.design_system.component.chart.data.ChartPoint

/**
 * Utility functions for formatting dates in the chart.
 */
internal object DateFormatter {

    /**
     * Extracts X-axis labels from chart points.
     * Formats dates as "DD Mon" (e.g., "15 Jan").
     *
     * @return List of formatted date strings
     */
    fun List<ChartPoint>.formatAsXAxisLabels(): List<String> {
        return this.map { point ->
            val monthName = point.date.month.name.take(3).lowercase()
            val capitalizedMonth = monthName.replaceFirstChar { it.titlecase() }
            "${point.date.day} $capitalizedMonth"
        }
    }
}