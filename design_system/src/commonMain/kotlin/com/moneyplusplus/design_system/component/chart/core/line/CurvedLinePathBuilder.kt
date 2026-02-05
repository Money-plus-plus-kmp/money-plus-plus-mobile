package com.moneyplusplus.design_system.component.chart.core.line

import androidx.compose.ui.graphics.Path

/**
 * Builds a curved line path using cubic Bezier curves.
 * Creates smooth curves between data points.
 */
internal object CurvedLinePathBuilder {
    
    /**
     * Builds a smooth curved path through all provided points.
     * Uses cubic Bezier curves with the midpoint as the control point.
     *
     * @param points List of (x, y) coordinates in pixels
     * @return A Path object representing the curved line
     */
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
    
    /**
     * Adds a cubic Bezier curve from the current position to the target point.
     * The control points are placed at the horizontal midpoint.
     */
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
    
    /**
     * Extends an existing path to include additional points.
     * Useful for building paths incrementally.
     *
     * @param existingPath The path to extend
     * @param newPoints Additional points to add
     * @return The extended path
     */
    fun extendPath(existingPath: Path, newPoints: List<Pair<Float, Float>>): Path {
        if (newPoints.isEmpty()) return existingPath
        
        newPoints.forEachIndexed { index, (x, y) ->
            if (index == 0) {
                // Connect to the new points
                existingPath.lineTo(x, y)
            } else {
                val previousPoint = newPoints[index - 1]
                addCubicCurveToPoint(
                    path = existingPath,
                    fromX = previousPoint.first,
                    fromY = previousPoint.second,
                    toX = x,
                    toY = y
                )
            }
        }
        
        return existingPath
    }
}
