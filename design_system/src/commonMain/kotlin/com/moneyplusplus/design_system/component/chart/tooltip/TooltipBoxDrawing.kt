package com.moneyplusplus.design_system.component.chart.tooltip

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import com.moneyplusplus.design_system.component.chart.constants.TooltipConstants

internal fun DrawScope.drawTooltipBox(
    position: Offset,
    size: Size,
    backgroundColor: Color
) {
    val cornerRadiusPx = TooltipConstants.CORNER_RADIUS.toPx()
    val bottomRightRadiusPx = TooltipConstants.BOTTOM_RIGHT_CORNER_RADIUS.toPx()
    
    val path = Path().apply {
        addRoundRect(
            RoundRect(
                rect = Rect(offset = position, size = size),
                topLeft = CornerRadius(cornerRadiusPx, cornerRadiusPx),
                topRight = CornerRadius(cornerRadiusPx, cornerRadiusPx),
                bottomLeft = CornerRadius(cornerRadiusPx, cornerRadiusPx),
                bottomRight = CornerRadius(bottomRightRadiusPx, bottomRightRadiusPx)
            )
        )
    }
    
    drawPath(
        path = path,
        color = backgroundColor,
        style = Fill
    )
}
