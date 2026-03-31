package com.moneyplusplus.design_system.component.chart.tooltip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions

internal fun calculateTooltipPosition(
    pointX: Dp,
    pointY: Float,
    tooltipWidth: Float,
    tooltipHeight: Float,
    density: (Dp) -> Float
): Offset {
    val x = density(pointX) - tooltipWidth
    val y = pointY - tooltipHeight - density(OFFSET_ABOVE_MARKER) - density(ChartDimensions.MARKER_RADIUS)
    return Offset(x, y)
}

internal fun calculateTooltipSize(
    textWidth: Int,
    textHeight: Int,
    paddingPx: Float
): Size {
    return Size(
        width = textWidth + (paddingPx * 2),
        height = textHeight + (paddingPx * 2)
    )
}

internal fun calculateTextPosition(
    tooltipPosition: Offset,
    paddingPx: Float
): Offset {
    return Offset(
        x = tooltipPosition.x + paddingPx,
        y = tooltipPosition.y + paddingPx
    )
}

private val OFFSET_ABOVE_MARKER = 2.dp