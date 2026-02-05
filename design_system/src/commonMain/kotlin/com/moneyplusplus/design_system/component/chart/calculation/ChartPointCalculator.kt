package com.moneyplusplus.design_system.component.chart.calculation

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions

/**
 * Calculates canvas coordinates for chart data points.
 * Separates the coordinate calculation logic from drawing logic.
 */
internal object ChartPointCalculator {
    
    /**
     * Calculates the X position for a data point on the canvas.
     *
     * @param index The index of the data point
     * @param startOffset The horizontal offset where the chart area starts (after Y-axis labels)
     * @param xRegionWidth The width allocated for each data point region
     * @return The X coordinate in Dp
     */
    fun calculateXPosition(
        index: Int,
        startOffset: Dp,
        xRegionWidth: Dp
    ): Dp = startOffset + (xRegionWidth * index)
    
    /**
     * Calculates the Y position for a data point on the canvas.
     *
     * @param value The data value to plot
     * @param lowerValue The minimum value on the Y-axis
     * @param upperValue The maximum value on the Y-axis
     * @param chartHeight The total height of the chart area in pixels
     * @param spacingY The bottom spacing reserved for X-axis labels in pixels
     * @param verticalOffset Additional vertical offset for line positioning in pixels
     * @return The Y coordinate in pixels
     */
    fun calculateYPosition(
        value: Double,
        lowerValue: Float,
        upperValue: Float,
        chartHeight: Float,
        spacingY: Float,
        verticalOffset: Float
    ): Float {
        val valueRange = upperValue - lowerValue
        if (valueRange == 0f) return chartHeight - spacingY
        
        val normalizedRatio = (value - lowerValue) / valueRange
        val availableHeight = chartHeight - spacingY
        
        return chartHeight + verticalOffset - spacingY - (normalizedRatio * availableHeight).toFloat()
    }
    
    /**
     * Calculates all point coordinates for a dataset.
     *
     * @param data The list of data values
     * @param startOffset The horizontal offset where the chart area starts
     * @param xRegionWidth The width allocated for each data point region
     * @param lowerValue The minimum value on the Y-axis
     * @param upperValue The maximum value on the Y-axis
     * @param chartHeight The total height of the chart area in pixels
     * @param spacingY The bottom spacing reserved for X-axis labels in pixels
     * @param verticalOffset Additional vertical offset for line positioning in pixels
     * @param density The density for converting Dp to pixels
     * @return List of (x, y) coordinates in pixels
     */
    fun calculateAllPoints(
        data: List<Double>,
        startOffset: Dp,
        xRegionWidth: Dp,
        lowerValue: Float,
        upperValue: Float,
        chartHeight: Float,
        spacingY: Float,
        verticalOffset: Float,
        density: Density
    ): List<Pair<Float, Float>> {
        return data.mapIndexed { index, value ->
            val xPosition = with(density) {
                calculateXPosition(index, startOffset, xRegionWidth).toPx()
            }
            val yPosition = calculateYPosition(
                value = value,
                lowerValue = lowerValue,
                upperValue = upperValue,
                chartHeight = chartHeight,
                spacingY = spacingY,
                verticalOffset = verticalOffset
            )
            xPosition to yPosition
        }
    }
}
