package com.moneyplusplus.design_system.component.chart.calculation

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

internal object ChartPointCalculator {
    fun calculateAllPoints(
        data: List<Double>,
        startOffset: Dp,
        xRegionWidth: Dp,
        lowerValue: Float,
        upperValue: Float,
        chartHeight: Float,
        xAxisLabelsHeight: Float,
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
                xAxisLabelsHeight = xAxisLabelsHeight,
                verticalOffset = verticalOffset
            )
            xPosition to yPosition
        }
    }

    private fun calculateXPosition(index: Int, startOffset: Dp, xRegionWidth: Dp): Dp {
        return startOffset + (xRegionWidth * index)
    }

    private fun calculateYPosition(
        value: Double,
        lowerValue: Float,
        upperValue: Float,
        chartHeight: Float,
        xAxisLabelsHeight: Float,
        verticalOffset: Float
    ): Float {
        val valueRange = upperValue - lowerValue
        if (valueRange == 0f) return chartHeight - xAxisLabelsHeight

        val normalizedRatio = (value - lowerValue) / valueRange
        val availableHeight = chartHeight - xAxisLabelsHeight

        return chartHeight + verticalOffset - xAxisLabelsHeight - (normalizedRatio * availableHeight).toFloat()
    }
}
