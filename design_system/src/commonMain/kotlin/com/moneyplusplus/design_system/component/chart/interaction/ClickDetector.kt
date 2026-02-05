package com.moneyplusplus.design_system.component.chart.interaction

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import kotlin.math.hypot

private val TOUCH_TOLERANCE = 20.dp

internal fun findClickedPointIndex(
    tapPosition: Offset,
    points: List<Pair<Float, Float>>,
    tolerancePx: Float = TOUCH_TOLERANCE.value
): Int? {
    return points.indexOfFirst { (x, y) ->
        isPointWithinTolerance(tapPosition, x, y, tolerancePx)
    }.takeIf { it >= 0 }
}

private fun isPointWithinTolerance(
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
