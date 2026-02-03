package com.moneyplusplus.design_system.component.chart.components.tooltip

import com.moneyplusplus.design_system.component.chart.models.ChartTooltipConfig
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
import com.moneyplusplus.design_system.component.chart.utils.formatWithCommas
import com.moneyplusplus.design_system.component.chart.utils.ChartConstants

@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.drawTooltipWithMarker(
    textMeasurer: TextMeasurer,
    xIndex: Int,
    yValue: Double,
    x: Dp,
    y: Double,
    xAxisData: List<String> = emptyList(),
    lineColor: Color,
    valueSuffix: String?,
    tooltipConfig: ChartTooltipConfig,
) {
    val xLabel = xAxisData.getOrNull(xIndex) ?: xIndex.toString()
    val formattedValue = yValue.toFloat().formatWithCommas()
    val tooltipText = "$xLabel\n$formattedValue $valueSuffix"

    val textStyle = TextStyle(
        fontSize = 12.sp,
        color = tooltipConfig.textColor
    )

    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(tooltipText),
        style = textStyle
    )

    // Layout
    val padding = ChartConstants.tooltipPadding
    val textWidth = textLayoutResult.size.width
    val textHeight = textLayoutResult.size.height
    val boxWidth = textWidth + (padding.toPx() * 2)
    val boxHeight = textHeight + (padding.toPx() * 2)

    val tooltipX = x.toPx() - boxWidth
    val tooltipY = y.toFloat() - (boxHeight) - 2.dp.toPx() - ChartConstants.markerRadius.toPx()

    // Draw Box
    val path = androidx.compose.ui.graphics.Path().apply {
        addRoundRect(
            androidx.compose.ui.geometry.RoundRect(
                rect = androidx.compose.ui.geometry.Rect(
                    offset = Offset(tooltipX, tooltipY),
                    size = Size(boxWidth, boxHeight)
                ),
                topLeft = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                topRight = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                bottomLeft = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                bottomRight = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx(), 2.dp.toPx())
            )
        )
    }

    drawPath(
        path = path,
        color = tooltipConfig.backgroundColor,
        style = Fill
    )

    // Draw Box Text
    drawText(
        textMeasurer = textMeasurer,
        text = tooltipText,
        style = textStyle,
        topLeft = Offset(tooltipX + padding.toPx(), tooltipY + padding.toPx())
    )
    
    drawCircle(
        color = lineColor,
        radius = ChartConstants.markerRadius.toPx(),
        center = Offset(x.toPx(), y.toFloat())
    )
}
