package com.moneyplusplus.design_system.component.chart.core.line

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions

internal object LineShadowRenderer {
    fun DrawScope.drawLineShadow(
        linePath: Path,
        lineColor: Color,
        chartHeight: Float,
        xAxisLabelsHeight: Float,
        xRegionWidth: Dp,
        animationProgress: Float
    ) {
        val fillPath = createFillPath(linePath, chartHeight, xRegionWidth)

        clipRect(right = size.width * animationProgress) {
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        lineColor.copy(alpha = SHADOW_ALPHA),
                        Color.Transparent
                    ),
                    endY = chartHeight - xAxisLabelsHeight
                )
            )
        }
    }

    private fun DrawScope.createFillPath(
        linePath: Path,
        chartHeight: Float,
        xRegionWidth: Dp
    ): Path {
        return Path().apply {
            addPath(linePath)

            lineTo(
                x = size.width - xRegionWidth.toPx() + SHADOW_OFFSET.toPx(),
                y = chartHeight * GRADIENT_FILL_BOTTOM_FACTOR
            )

            lineTo(
                x = ChartDimensions.POINT_WIDTH.toPx() * 2,
                y = chartHeight * GRADIENT_FILL_BOTTOM_FACTOR
            )

            close()
        }
    }

    private val SHADOW_OFFSET = 40.dp
    private const val GRADIENT_FILL_BOTTOM_FACTOR = 40f
    private const val SHADOW_ALPHA = 0.32f
}