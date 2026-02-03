package com.moneyplusplus.design_system.chart.components.tooltip

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneyplusplus.design_system.chart.utils.formatWithCommas

@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.drawTooltipWithMarker(
    textMeasurer: TextMeasurer,
    xIndex: Int,
    yValue: Double,
    x: Dp,
    y: Double,
    xAxisData: List<String> = emptyList(),
    lineColor: Color,
    valueSuffix: String,
    tooltipBackgroundColor: Color,
    tooltipTextColor: Color
) {
    drawCircle(
        color = lineColor,
        radius = 4.dp.toPx(),
        center = Offset(x.toPx(), y.toFloat())
    )

    val xLabel = xAxisData.getOrNull(xIndex) ?: xIndex.toString()
    val formattedValue = yValue.toFloat().formatWithCommas()
    val tooltipText = "$xLabel\n$formattedValue $valueSuffix"

    val textStyle = TextStyle(
        fontSize = 12.sp,
        color = tooltipTextColor
    )

    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(tooltipText),
        style = textStyle
    )

    // Layout
    val padding = 8.dp
    val textWidth = textLayoutResult.size.width
    val textHeight = textLayoutResult.size.height
    val boxWidth = textWidth + (padding.toPx() * 2)
    val boxHeight = textHeight + (padding.toPx() * 2)

    val tooltipX = x.toPx() - (boxWidth / 2f)
    val tooltipY = y.toFloat() - boxHeight - 10.dp.toPx()

    // Draw Box
    drawRoundRect(
        color = tooltipBackgroundColor,
        topLeft = Offset(tooltipX, tooltipY),
        size = Size(boxWidth, boxHeight),
        cornerRadius = CornerRadius(8.dp.toPx()),
        style = Fill
    )

    // Draw Box Text
    drawText(
        textMeasurer = textMeasurer,
        text = tooltipText,
        style = textStyle,
        topLeft = Offset(tooltipX + padding.toPx(), tooltipY + padding.toPx())
    )
}
