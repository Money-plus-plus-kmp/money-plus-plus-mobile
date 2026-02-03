package com.moneyplusplus.design_system.component.chart.components.grid

import com.moneyplusplus.design_system.component.chart.components.axis.xAxisDrawing
import com.moneyplusplus.design_system.component.chart.components.axis.yAxisDrawing
import com.moneyplusplus.design_system.component.chart.models.ChartConfig
import com.moneyplusplus.design_system.component.chart.utils.ChartConstants
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
    config: ChartConfig,
    xRegionWidth: Dp
) {
    val spacingY = ChartConstants.spacingY
    xAxisDrawing(
        xAxisData = xAxisData,
        textMeasure = textMeasure,
        xAxisStyle = config.styles.axisLabel,
        upperValue = upperValue,
        xRegionWidth = xRegionWidth
    )

    yAxisDrawing(
        upperValue = upperValue,
        lowerValue = lowerValue,
        textMeasure = textMeasure,
        spacing = spacingY,
        yAxisStyle = config.styles.axisLabel,
        yAxisRange = config.yAxisRange
    )

    grid(
        gridColor = config.colors.gridColor,
        backgroundLineWidth = ChartConstants.backgroundLineWidth.toPx(),
        showGridWithSpacer = true,
        yAxisRange = config.yAxisRange,
        upperValue = upperValue,
        textMeasurer = textMeasure
    )
}
