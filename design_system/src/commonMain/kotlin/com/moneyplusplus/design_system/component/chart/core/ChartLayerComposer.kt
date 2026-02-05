package com.moneyplusplus.design_system.component.chart.core

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.core.axis.drawXAxisLabels
import com.moneyplusplus.design_system.component.chart.core.axis.drawYAxisLabels
import com.moneyplusplus.design_system.component.chart.core.grid.drawGridLines

@OptIn(ExperimentalTextApi::class)
internal fun <T> DrawScope.composeChartLayers(
    xAxisLabels: List<T>,
    textMeasurer: TextMeasurer,
    upperValue: Float,
    lowerValue: Float,
    config: ChartConfig,
    xRegionWidth: Dp
) {
    val xAxisLabelsHeight = ChartDimensions.X_AXIS_LABELS_HEIGHT.toPx()

    drawXAxisLabels(
        labels = xAxisLabels,
        textMeasurer = textMeasurer,
        textStyle = config.styles.axisLabel,
        upperValue = upperValue,
        xRegionWidth = xRegionWidth
    )

    drawYAxisLabels(
        upperValue = upperValue,
        lowerValue = lowerValue,
        textMeasurer = textMeasurer,
        xAxisLabelsHeight = xAxisLabelsHeight,
        textStyle = config.styles.axisLabel,
        numberOfLabels = config.yAxisRange
    )

    drawGridLines(
        gridColor = config.colors.gridColor,
        numberOfLines = config.yAxisRange,
        upperValue = upperValue,
        textMeasurer = textMeasurer,
        xAxisLabelsHeight = xAxisLabelsHeight,
        showDashedLines = true
    )
}
