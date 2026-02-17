package com.moneyplusplus.design_system.component.chart.tooltip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions

internal fun DrawScope.drawMarkerCircle(
    center: Offset,
    color: Color,
    radius: Dp = ChartDimensions.MARKER_RADIUS
) {
    drawCircle(
        color = color,
        radius = radius.toPx(),
        center = center
    )
}
