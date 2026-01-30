package com.moneyplusplus.design_system.chart.config

import com.aay.compose.lineChart.model.LineType


data class SpendingChartConfig(
    val colorScheme: ChartColorScheme = ChartColorScheme.RED,
    val valueFormat: ValueFormat = ValueFormat.NUMBER,
    val showGrid: Boolean = true,
    val showXAxis: Boolean = true,
    val showYAxis: Boolean = true,
    val smoothCurve: Boolean = true,
    val showShadow: Boolean = true,
    val animate: Boolean = true,
    val yAxisSteps: Int = 5,
    val datePattern: DatePattern = DatePattern.DAY_MONTH_SHORT
) {
    internal val lineType: LineType
        get() = if (smoothCurve) LineType.CURVED_LINE else LineType.DEFAULT_LINE

    companion object {
        val DEFAULT = SpendingChartConfig()
    }
}


enum class DatePattern {
    /** "1 Dec" */
    DAY_MONTH_SHORT,
    /** "Dec" */
    MONTH_SHORT,
    /** "Mon" */
    WEEKDAY_SHORT,
    /** "1" */
    DAY_ONLY
}