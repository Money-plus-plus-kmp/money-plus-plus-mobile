package com.moneyplusplus.design_system.component.chart.core.line

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp

internal object LineRenderer {
    fun DrawScope.drawAnimatedLine(
        path: Path,
        lineColor: Color,
        animationProgress: Float,
        canvasWidth: Float = size.width
    ) {
        val lineStrokeWidth = 3.dp
        clipRect(right = canvasWidth * animationProgress) {
            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(
                    width = lineStrokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}
