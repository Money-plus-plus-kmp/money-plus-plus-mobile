package com.moneyplusplus.design_system.component.chart.tooltip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.config.ChartTooltipConfig
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.drawTooltipWithMarker(
    textMeasurer: TextMeasurer,
    pointIndex: Int,
    pointValue: Double,
    pointX: Dp,
    pointY: Float,
    xAxisLabels: List<String>,
    lineColor: Color,
    valueSuffix: String?,
    tooltipConfig: ChartTooltipConfig
) {
    val xLabel = xAxisLabels.getOrNull(pointIndex) ?: pointIndex.toString()
    val formattedValue = NumberFormatter.formatWithCommas(pointValue.toFloat())
    val suffixText = valueSuffix?.let { " $it" } ?: ""
    val tooltipText = "$xLabel\n$formattedValue$suffixText"

    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(tooltipText),
        style = tooltipConfig.textStyle
    )

    val paddingPx = PADDING.toPx()
    val tooltipSize = calculateTooltipSize(
        textWidth = textLayoutResult.size.width,
        textHeight = textLayoutResult.size.height,
        paddingPx = paddingPx
    )

    val tooltipPosition = calculateTooltipPosition(
        pointX = pointX,
        pointY = pointY,
        tooltipWidth = tooltipSize.width,
        tooltipHeight = tooltipSize.height,
        density = { it.toPx() }
    )

    val textPosition = calculateTextPosition(
        tooltipPosition = tooltipPosition,
        paddingPx = paddingPx
    )

    drawTooltipBox(
        position = tooltipPosition,
        size = tooltipSize,
        backgroundColor = tooltipConfig.backgroundColor
    )

    drawTooltipText(
        textMeasurer = textMeasurer,
        text = tooltipText,
        textStyle = tooltipConfig.textStyle,
        position = textPosition
    )

    drawMarkerCircle(
        center = Offset(pointX.toPx(), pointY),
        color = lineColor
    )
}

private val PADDING = 6.dp

