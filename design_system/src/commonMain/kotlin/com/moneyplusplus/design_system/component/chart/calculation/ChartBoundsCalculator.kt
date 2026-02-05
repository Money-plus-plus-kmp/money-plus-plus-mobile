package com.moneyplusplus.design_system.component.chart.calculation

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions

/**
 * Calculates chart bounds and spacing based on text measurements.
 * Centralizes the repeated textSpace calculation that appears in multiple files.
 */
internal object ChartBoundsCalculator {
    
    /**
     * Calculates the text space based on the maximum Y-axis label width.
     * This space is reserved on the left side of the chart for Y-axis labels.
     *
     * @param maxYValueTextWidth The measured width of the largest Y-axis label in pixels
     * @return The calculated text space in pixels
     */
    fun calculateTextSpace(maxYValueTextWidth: Int): Int {
        return maxYValueTextWidth - (maxYValueTextWidth / 4)
    }
    
    /**
     * Calculates the horizontal offset where the chart drawing area starts.
     * This accounts for the Y-axis label space plus additional padding.
     *
     * @param maxYValueTextWidth The measured width of the largest Y-axis label in pixels
     * @param density The density for converting pixels to Dp
     * @return The start offset in Dp
     */
    fun calculateStartOffset(
        maxYValueTextWidth: Int,
        density: Density
    ): Dp {
        val textSpace = calculateTextSpace(maxYValueTextWidth)
        return with(density) { textSpace.toDp() } + ChartDimensions.TEXT_SPACING
    }
    
    /**
     * Calculates the width of each X-axis region (horizontal space per data point).
     *
     * @param totalWidth The total available width for the chart
     * @param startOffset The horizontal offset where the chart area starts
     * @param pointCount The number of data points
     * @return The width per region in Dp
     */
    fun calculateXRegionWidth(
        totalWidth: Dp,
        startOffset: Dp,
        pointCount: Int
    ): Dp {
        return if (pointCount > 1) {
            (totalWidth - startOffset) / (pointCount - 1)
        } else {
            totalWidth
        }
    }
    
    /**
     * Calculates the total width needed for the chart content.
     *
     * @param dataSize The number of data points
     * @param startOffset The horizontal offset for Y-axis labels
     * @return The calculated total width in Dp
     */
    fun calculateChartWidth(
        dataSize: Int,
        startOffset: Dp
    ): Dp {
        return (ChartDimensions.POINT_WIDTH * dataSize) + startOffset + ChartDimensions.MIN_WIDTH_PADDING
    }
}
