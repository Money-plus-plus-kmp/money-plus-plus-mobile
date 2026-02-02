package com.moneyplusplus.design_system.chart.drawing

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
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
import com.moneyplusplus.design_system.chart.models.LineParameters
import com.moneyplusplus.design_system.chart.models.TooltipConfig
import com.moneyplusplus.design_system.chart.utils.formatToThousandsMillionsBillions

@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.drawTooltipWithMarker(
    animatedProgress: Animatable<Float, AnimationVector1D>,
    textMeasurer: TextMeasurer,
    xIndex: Int,
    yValue: Double,
    line: LineParameters,
    x: Dp,
    y: Double,
    xAxisData: List<String> = emptyList(),
) {
    val config = line.tooltipConfig
    if (!config.enabled) return

    // Draw solid marker circle
    drawCircle(
        color = line.lineColor,
        radius = 4.dp.toPx(),
        center = Offset(x.toPx(), y.toFloat())
    )

    // Generate text: "Label: Value" or just "Value"
    val xLabel = xAxisData.getOrNull(xIndex) ?: xIndex.toString()
    val tooltipText = "${line.label}\n$xLabel: ${yValue.toFloat().formatToThousandsMillionsBillions()}"

    val textStyle = TextStyle(
        fontSize = 12.sp, // Hardcoded size
        color = config.textColor
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
        color = config.backgroundColor,
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
