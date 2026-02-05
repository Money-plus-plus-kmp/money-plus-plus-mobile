package com.moneyplusplus.design_system.component.chart.core.line

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions

/**
 * Renders the gradient shadow beneath the chart line.
 * This creates the fill effect that makes the chart more visually appealing.
 */
internal object LineShadowRenderer {
    
    /**
     * Draws a gradient shadow beneath the line.
     *
     * @param linePath The line path to create shadow from
     * @param lineColor The base color for the gradient
     * @param chartHeight The height of the chart area in pixels
     * @param spacingY The bottom spacing in pixels
     * @param xRegionWidth The width of each X region
     * @param animationProgress Progress of the drawing animation (0.0 to 1.0)
     */
    fun DrawScope.drawLineShadow(
        linePath: Path,
        lineColor: Color,
        chartHeight: Float,
        spacingY: Float,
        xRegionWidth: Dp,
        animationProgress: Float
    ) {
        val fillPath = createFillPath(linePath, chartHeight, xRegionWidth)
        
        clipRect(right = size.width * animationProgress) {
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        lineColor.copy(alpha = ChartDimensions.SHADOW_ALPHA),
                        Color.Transparent
                    ),
                    endY = chartHeight - spacingY
                )
            )
        }
    }
    
    /**
     * Creates the fill path by closing the line path to form an area.
     */
    private fun DrawScope.createFillPath(
        linePath: Path,
        chartHeight: Float,
        xRegionWidth: Dp
    ): Path {
        return Path().apply {
            addPath(linePath)
            
            // Draw line to bottom right
            lineTo(
                x = size.width - xRegionWidth.toPx() + ChartDimensions.SHADOW_OFFSET.toPx(),
                y = chartHeight * ChartDimensions.GRADIENT_FILL_BOTTOM_FACTOR
            )
            
            // Draw line to bottom left
            lineTo(
                x = ChartDimensions.POINT_WIDTH.toPx() * 2,
                y = chartHeight * ChartDimensions.GRADIENT_FILL_BOTTOM_FACTOR
            )
            
            close()
        }
    }
    
    /**
     * Draws the shadow without animation (full visibility).
     */
    fun DrawScope.drawLineShadowStatic(
        linePath: Path,
        lineColor: Color,
        chartHeight: Float,
        spacingY: Float,
        xRegionWidth: Dp
    ) {
        drawLineShadow(
            linePath = linePath,
            lineColor = lineColor,
            chartHeight = chartHeight,
            spacingY = spacingY,
            xRegionWidth = xRegionWidth,
            animationProgress = 1f
        )
    }
}
