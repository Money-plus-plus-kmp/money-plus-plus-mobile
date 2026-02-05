package com.moneyplusplus.design_system.component.chart.tooltip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.constants.TooltipConstants

internal object TooltipPositionCalculator {

    /**
     * Calculates the tooltip box position above the marker.
     *
     * @param pointX The X coordinate of the selected point in Dp
     * @param pointY The Y coordinate of the selected point in pixels
     * @param tooltipWidth The width of the tooltip box in pixels
     * @param tooltipHeight The height of the tooltip box in pixels
     * @param density Function to convert Dp to pixels
     * @return The top-left position of the tooltip box
     */
    fun calculateTooltipPosition(
        pointX: Dp,
        pointY: Float,
        tooltipWidth: Float,
        tooltipHeight: Float,
        density: (Dp) -> Float
    ): Offset {
        val x = density(pointX) - tooltipWidth
        val y = pointY - tooltipHeight - density(TooltipConstants.OFFSET_ABOVE_MARKER) - density(ChartDimensions.MARKER_RADIUS)
        return Offset(x, y)
    }

    /**
     * Calculates the size of the tooltip box based on text dimensions.
     *
     * @param textWidth The width of the text content in pixels
     * @param textHeight The height of the text content in pixels
     * @param paddingPx The padding inside the tooltip in pixels
     * @return The size of the tooltip box
     */
    fun calculateTooltipSize(
        textWidth: Int,
        textHeight: Int,
        paddingPx: Float
    ): Size {
        return Size(
            width = textWidth + (paddingPx * 2),
            height = textHeight + (paddingPx * 2)
        )
    }

    /**
     * Calculates the position for the text inside the tooltip.
     *
     * @param tooltipPosition The top-left position of the tooltip box
     * @param paddingPx The padding inside the tooltip in pixels
     * @return The top-left position for the text
     */
    fun calculateTextPosition(
        tooltipPosition: Offset,
        paddingPx: Float
    ): Offset {
        return Offset(
            x = tooltipPosition.x + paddingPx,
            y = tooltipPosition.y + paddingPx
        )
    }
}
