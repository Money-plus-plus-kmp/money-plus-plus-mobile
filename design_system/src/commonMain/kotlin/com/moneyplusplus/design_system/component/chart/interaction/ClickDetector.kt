package com.moneyplusplus.design_system.component.chart.interaction

import androidx.compose.ui.geometry.Offset
import kotlin.math.hypot

/**
 * Detects which chart point was clicked based on tap position.
 * Contains pure functions for click/touch detection logic.
 */
internal object ClickDetector {
    
    /**
     * Finds the index of the point that was clicked, if any.
     *
     * @param tapPosition The position where the user tapped
     * @param points List of (x, y) coordinates for all data points
     * @param tolerance The maximum distance from a point to consider it clicked
     * @return The index of the clicked point, or null if no point was within tolerance
     */
    fun findClickedPointIndex(
        tapPosition: Offset,
        points: List<Pair<Float, Float>>,
        tolerance: Float
    ): Int? {
        return points.indexOfFirst { (x, y) ->
            isPointWithinTolerance(tapPosition, x, y, tolerance)
        }.takeIf { it >= 0 }
    }
    
    /**
     * Checks if a tap position is within tolerance of a specific point.
     *
     * @param tapPosition The position where the user tapped
     * @param pointX The X coordinate of the point
     * @param pointY The Y coordinate of the point
     * @param tolerance The maximum distance to consider as a hit
     * @return True if the tap is within tolerance of the point
     */
    fun isPointWithinTolerance(
        tapPosition: Offset,
        pointX: Float,
        pointY: Float,
        tolerance: Float
    ): Boolean {
        val distance = hypot(
            (tapPosition.x - pointX).toDouble(),
            (tapPosition.y - pointY).toDouble()
        )
        return distance <= tolerance
    }
    
    /**
     * Checks if any point in a list of clicks matches a target point.
     * This is for backward compatibility with the existing click handling.
     *
     * @param clickedPoints List of click positions
     * @param targetX The X coordinate to check
     * @param targetY The Y coordinate to check
     * @param tolerance The maximum distance to consider as a match
     * @return True if any click matches the target point
     */
    fun hasClickNearPoint(
        clickedPoints: List<Pair<Float, Float>>,
        targetX: Float,
        targetY: Float,
        tolerance: Float
    ): Boolean {
        return clickedPoints.any { (clickX, clickY) ->
            val distance = hypot(
                (clickX - targetX).toDouble(),
                (clickY - targetY).toDouble()
            )
            distance <= tolerance
        }
    }
}
