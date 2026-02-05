package com.moneyplusplus.design_system.component.chart.tooltip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.config.ChartTooltipConfig
import com.moneyplusplus.design_system.component.chart.constants.TooltipConstants
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

/**
 * Renders a complete tooltip with marker at the selected point.
 * This is the main entry point that composes all tooltip drawing components.
 */
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
    // Format tooltip text
    val xLabel = xAxisLabels.getOrNull(pointIndex) ?: pointIndex.toString()
    val formattedValue = NumberFormatter.formatWithCommas(pointValue.toFloat())
    val suffixText = valueSuffix?.let { " $it" } ?: ""
    val tooltipText = "$xLabel\n$formattedValue$suffixText"
    
    // Measure text
    val textStyle = TextStyle(
        fontSize = TooltipConstants.FONT_SIZE,
        color = tooltipConfig.textColor
    )
    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(tooltipText),
        style = textStyle
    )
    
    // Calculate sizes and positions
    val paddingPx = TooltipConstants.PADDING.toPx()
    val tooltipSize = TooltipPositionCalculator.calculateTooltipSize(
        textWidth = textLayoutResult.size.width,
        textHeight = textLayoutResult.size.height,
        paddingPx = paddingPx
    )
    
    val tooltipPosition = TooltipPositionCalculator.calculateTooltipPosition(
        pointX = pointX,
        pointY = pointY,
        tooltipWidth = tooltipSize.width,
        tooltipHeight = tooltipSize.height,
        density = { it.toPx() }
    )
    
    val textPosition = TooltipPositionCalculator.calculateTextPosition(
        tooltipPosition = tooltipPosition,
        paddingPx = paddingPx
    )
    
    // Draw components
    drawTooltipBox(
        position = tooltipPosition,
        size = tooltipSize,
        backgroundColor = tooltipConfig.backgroundColor
    )
    
    drawTooltipText(
        textMeasurer = textMeasurer,
        text = tooltipText,
        textColor = tooltipConfig.textColor,
        position = textPosition
    )
    
    drawMarkerCircle(
        center = Offset(pointX.toPx(), pointY),
        color = lineColor
    )
}
