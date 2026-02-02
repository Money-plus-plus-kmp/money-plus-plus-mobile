package com.moneyplusplus.design_system.chart.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalTextApi::class)
internal fun <T> DrawScope.baseChartContainer(
    xAxisData: List<T>,
    textMeasure: TextMeasurer,
    upperValue: Float,
    lowerValue: Float,
    gridColor: Color,
    backgroundLineWidth: Float,
    showGridWithSpacer: Boolean,
    spacingY: Dp,
    yAxisStyle: TextStyle,
    xAxisStyle: TextStyle,
    yAxisRange: Int,
    xRegionWidth: Dp
) {
    xAxisDrawing(
        xAxisData = xAxisData,
        textMeasure = textMeasure,
        xAxisStyle = xAxisStyle,
        upperValue = upperValue,
        xRegionWidth = xRegionWidth
    )

    yAxisDrawing(
        upperValue = upperValue,
        lowerValue = lowerValue,
        textMeasure = textMeasure,
        spacing = spacingY,
        yAxisStyle = yAxisStyle,
        yAxisRange = yAxisRange
    )

    grid(
        gridColor = gridColor,
        backgroundLineWidth = backgroundLineWidth,
        showGridWithSpacer = showGridWithSpacer,
        spacingY = spacingY,
        yAxisRange = yAxisRange,
        upperValue = upperValue,
        textMeasurer = textMeasure
    )
}
