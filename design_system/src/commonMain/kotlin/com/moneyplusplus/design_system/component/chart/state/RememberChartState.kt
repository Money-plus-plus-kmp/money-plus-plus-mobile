package com.moneyplusplus.design_system.component.chart.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.moneyplusplus.design_system.component.chart.calculation.ChartLayoutCalculator
import com.moneyplusplus.design_system.component.chart.calculation.calculateAllPoints
import com.moneyplusplus.design_system.component.chart.calculation.calculateLowerBound
import com.moneyplusplus.design_system.component.chart.calculation.calculateUpperBound
import com.moneyplusplus.design_system.component.chart.core.line.CurvedLinePathBuilder
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

private val X_AXIS_LABELS_HEIGHT = 50.dp
private val VERTICAL_OFFSET = 11.dp

@OptIn(ExperimentalTextApi::class)
@Composable
internal fun rememberChartState(
    data: List<Double>,
    xAxisLabels: List<String>,
    screenWidth: Dp,
    chartHeight: Dp,
    textMeasurer: TextMeasurer
): ChartState {
    val density = LocalDensity.current

    return remember(data, xAxisLabels, screenWidth, chartHeight) {
        val upperValue = calculateUpperBound(data).toFloat()
        val lowerValue = calculateLowerBound(data).toFloat()

        val yMaxTextWidth = textMeasurer.measure(
            text = AnnotatedString(NumberFormatter.formatCompactNumber(upperValue))
        ).size.width

        val startOffset = ChartLayoutCalculator.calculateChartStartOffset(yMaxTextWidth, density)
        val calculatedWidth = ChartLayoutCalculator.calculateChartWidth(data.size, startOffset)
        val chartWidth = max(screenWidth, calculatedWidth)
        val xAxisSegmentWidth = ChartLayoutCalculator.calculateXAxisSegmentWidth(
            chartWidth,
            startOffset,
            xAxisLabels.size
        )

        val xAxisLabelsHeight = with(density) { X_AXIS_LABELS_HEIGHT.toPx() }

        val points = calculateAllPoints(
            data = data,
            startOffset = startOffset,
            xRegionWidth = xAxisSegmentWidth,
            lowerValue = lowerValue,
            upperValue = upperValue,
            chartHeight = with(density) { chartHeight.toPx() },
            xAxisLabelsHeight = xAxisLabelsHeight,
            verticalOffset = with(density) { VERTICAL_OFFSET.toPx() },
            density = density
        )

        val linePath = CurvedLinePathBuilder.buildPath(points)

        ChartState(
            points = points,
            linePath = linePath,
            upperValue = upperValue,
            lowerValue = lowerValue,
            chartWidth = chartWidth,
            xAxisSegmentWidth = xAxisSegmentWidth,
            startOffset = startOffset,
            xAxisLabelsHeight = xAxisLabelsHeight
        )
    }
}
