package com.moneyplusplus.design_system.component.chart.core.grid

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.calculation.ChartLayoutCalculator
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

internal fun DrawScope.drawGridLines(
    gridColor: Color,
    numberOfLines: Int,
    upperValue: Float,
    textMeasurer: TextMeasurer,
    xAxisLabelsHeight: Float,
    showDashedLines: Boolean = true
) {
    val yMaxTextWidth = textMeasurer.measure(
        text = AnnotatedString(NumberFormatter.formatCompactNumber(upperValue))
    ).size.width

    val yAxisLabelsWidth = ChartLayoutCalculator.calculateYAxisLabelsWidth(yMaxTextWidth)
    val xStart = (yAxisLabelsWidth.toDp() + ChartDimensions.TEXT_SPACING).toPx()
    val xEnd = size.width - (yAxisLabelsWidth / 0.9f).toDp().toPx()

    val pathEffect = if (showDashedLines) {
        PathEffect.dashPathEffect(floatArrayOf(DASH_INTERVAL, DASH_INTERVAL), 0f)
    } else {
        null
    }

    (0..numberOfLines).forEach { lineIndex ->
        val y = calculateGridLineY(lineIndex, numberOfLines, xAxisLabelsHeight)

        drawLine(
            color = gridColor,
            start = Offset(xStart, y),
            end = Offset(xEnd, y),
            strokeWidth = LINE_WIDTH.toPx(),
            pathEffect = pathEffect
        )
    }
}

private fun DrawScope.calculateGridLineY(
    lineIndex: Int,
    numberOfLines: Int,
    xAxisLabelsHeight: Float
): Float {
    val chartDrawableHeight = size.height - xAxisLabelsHeight
    val baseY = size.height - xAxisLabelsHeight - (lineIndex * chartDrawableHeight / numberOfLines)
    return baseY + VERTICAL_ALIGNMENT_OFFSET.toPx()
}

private val LINE_WIDTH = 1.dp
private val VERTICAL_ALIGNMENT_OFFSET = 9.dp
private const val DASH_INTERVAL = 16f