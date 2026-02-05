package com.moneyplusplus.design_system.component.chart.core.line

import androidx.compose.ui.graphics.Path

internal object CurvedLinePathBuilder {

    fun buildPath(points: List<Pair<Float, Float>>): Path {
        if (points.isEmpty()) return Path()

        return Path().apply {
            points.forEachIndexed { index, (x, y) ->
                if (index == 0) {
                    moveTo(x, y)
                } else {
                    val previousPoint = points[index - 1]
                    addCubicCurveToPoint(
                        path = this,
                        fromX = previousPoint.first,
                        fromY = previousPoint.second,
                        toX = x,
                        toY = y
                    )
                }
            }
        }
    }

    private fun addCubicCurveToPoint(
        path: Path,
        fromX: Float,
        fromY: Float,
        toX: Float,
        toY: Float
    ) {
        val midX = (fromX + toX) / 2f
        path.cubicTo(
            x1 = midX,
            y1 = fromY,
            x2 = midX,
            y2 = toY,
            x3 = toX,
            y3 = toY
        )
    }
}
