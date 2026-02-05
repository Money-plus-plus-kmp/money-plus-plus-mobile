package com.moneyplusplus.design_system.component.chart.core.line

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions

/**
 * Renders the chart line path with animation support.
 */
internal object LineRenderer {
    
    /**
     * Draws the line path with animation clipping.
     *
     * @param path The path to draw
     * @param lineColor The color of the line
     * @param animationProgress Progress of the drawing animation (0.0 to 1.0)
     * @param canvasWidth The total width of the canvas for clipping
     */
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
                    width = ChartDimensions.LINE_STROKE_WIDTH.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
    
    /**
     * Draws the line path without animation (full visibility).
     *
     * @param path The path to draw
     * @param lineColor The color of the line
     */
    fun DrawScope.drawLine(
        path: Path,
        lineColor: Color
    ) {
        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(
                width = ChartDimensions.LINE_STROKE_WIDTH.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}
