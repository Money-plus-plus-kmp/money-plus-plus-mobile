package com.moneyplusplus.design_system.chart.mapper

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.aay.compose.lineChart.model.LineParameters
import com.moneyplusplus.design_system.chart.config.DateFormatter
import com.moneyplusplus.design_system.chart.config.DatePattern
import com.moneyplusplus.design_system.chart.config.SpendingChartConfig
import com.moneyplusplus.design_system.chart.data.ChartData

object ChartDataMapper {

    /**
     * Converts ChartData to AAY-Chart's xAxisData (list of date labels).
     */
    fun toXAxisData(
        data: ChartData,
        datePattern: DatePattern
    ): List<String> {
        return data.points.map { point ->
            DateFormatter.format(point.date, datePattern)
        }
    }

    /**
     * Converts ChartData to AAY-Chart's LineParameters.
     */
    fun toLineParameters(
        data: ChartData,
        config: SpendingChartConfig,
        label: String = "spending"
    ): List<LineParameters> {
        return listOf(
            LineParameters(
                label = label,
                data = data.points.map { it.value },
                lineColor = config.colorScheme.lineColor,
                lineType = config.lineType,
                lineShadow = config.showShadow
            )
        )
    }

    /**
     * Creates Y-axis text style from config.
     */
    fun toYAxisStyle(config: SpendingChartConfig): TextStyle {
        return TextStyle(
            fontSize = 12.sp,
            color = config.colorScheme.axisLabelColor
        )
    }

    /**
     * Creates X-axis text style from config.
     */
    fun toXAxisStyle(config: SpendingChartConfig): TextStyle {
        return TextStyle(
            fontSize = 12.sp,
            color = config.colorScheme.axisLabelColor
        )
    }
}