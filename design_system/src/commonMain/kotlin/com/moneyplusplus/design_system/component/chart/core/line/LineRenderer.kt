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
        clipRect(right = canvasWidth * animationProgress) {
            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(
                    width = LINE_STROKE_WIDTH.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}
private val LINE_STROKE_WIDTH = 3.dp
