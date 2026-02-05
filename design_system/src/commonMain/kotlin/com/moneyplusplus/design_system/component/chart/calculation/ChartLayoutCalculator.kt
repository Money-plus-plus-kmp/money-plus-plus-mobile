package com.moneyplusplus.design_system.component.chart.calculation

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions

internal object ChartLayoutCalculator {

    fun calculateYAxisLabelsWidth(maxYValueTextWidth: Int): Int {
        return maxYValueTextWidth - (maxYValueTextWidth / 4)
    }

    fun calculateChartStartOffset(maxYValueTextWidth: Int, density: Density): Dp {
        val yAxisLabelsWidth = calculateYAxisLabelsWidth(maxYValueTextWidth)
        return with(density) { yAxisLabelsWidth.toDp() } + ChartDimensions.TEXT_SPACING
    }

    fun calculateXAxisSegmentWidth(totalWidth: Dp, startOffset: Dp, pointCount: Int): Dp {
        return if (pointCount > 1) {
            (totalWidth - startOffset) / (pointCount - 1)
        } else {
            totalWidth
        }
    }

    fun calculateChartWidth(dataSize: Int, startOffset: Dp): Dp {
        return (ChartDimensions.POINT_WIDTH * dataSize) + startOffset + MIN_WIDTH_PADDING
    }
}

private val MIN_WIDTH_PADDING = 20.dp
