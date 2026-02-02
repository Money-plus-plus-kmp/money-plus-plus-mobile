package com.moneyplusplus.design_system.chart.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

/**
 * Utility for building chart paths
 */
object PathBuilder {

    /**
     * Build a smooth curved path through points
     */
    fun buildSmoothPath(points: List<Offset>): Path {
        val path = Path()

        if (points.isEmpty()) return path

        path.moveTo(points.first().x, points.first().y)

        if (points.size == 1) return path

        for (i in 0 until points.size - 1) {
            val current = points[i]
            val next = points[i + 1]

            val controlX1 = current.x + (next.x - current.x) / 3
            val controlY1 = current.y
            val controlX2 = current.x + 2 * (next.x - current.x) / 3
            val controlY2 = next.y

            path.cubicTo(controlX1, controlY1, controlX2, controlY2, next.x, next.y)
        }

        return path
    }

    /**
     * Build a straight-line path through points
     */
    fun buildLinearPath(points: List<Offset>): Path {
        val path = Path()

        if (points.isEmpty()) return path

        path.moveTo(points.first().x, points.first().y)
        points.drop(1).forEach { point ->
            path.lineTo(point.x, point.y)
        }

        return path
    }

    /**
     * Build a closed path for gradient fill
     */
    fun buildGradientPath(
        points: List<Offset>,
        chartHeight: Float,
        horizontalPadding: Float,
        smooth: Boolean = true
    ): Path {
        val linePath = if (smooth) buildSmoothPath(points) else buildLinearPath(points)

        if (points.isEmpty()) return linePath

        linePath.lineTo(points.last().x, chartHeight)
        linePath.lineTo(points.first().x, chartHeight)
        linePath.close()

        return linePath
    }
}