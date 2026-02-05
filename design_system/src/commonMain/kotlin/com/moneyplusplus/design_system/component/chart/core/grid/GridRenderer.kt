package com.moneyplusplus.design_system.component.chart.core.grid

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import com.moneyplusplus.design_system.component.chart.calculation.ChartBoundsCalculator
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

/**
 * Renders the horizontal grid lines across the chart.
 */
internal object GridRenderer {

    /**
     * Draws dashed horizontal grid lines at each Y-axis interval.
     *
     * @param gridColor The color of the grid lines
     * @param yAxisRange The number of intervals on the Y-axis
     * @param upperValue The maximum value on the Y-axis
     * @param textMeasurer The text measurer for calculating label widths
     * @param showDashedLines Whether to show dashed lines (true) or solid lines (false)
     */
    @OptIn(ExperimentalTextApi::class)
    fun DrawScope.drawGridLines(
        gridColor: Color,
        yAxisRange: Int,
        upperValue: Float,
        textMeasurer: TextMeasurer,
        showDashedLines: Boolean = true
    ) {
        val yMaxTextWidth = textMeasurer.measure(
            text = AnnotatedString(NumberFormatter.formatCompactNumber(upperValue))
        ).size.width
        
        val textSpace = ChartBoundsCalculator.calculateTextSpace(yMaxTextWidth)
        val xStart = (textSpace.toDp() + ChartDimensions.TEXT_SPACING).toPx()
        val xEnd = size.width - (textSpace / 0.9f).toDp().toPx()
        
        val pathEffect = if (showDashedLines) {
            PathEffect.dashPathEffect(
                floatArrayOf(ChartDimensions.GRID_DASH_INTERVAL, ChartDimensions.GRID_DASH_INTERVAL),
                0f
            )
        } else {
            null
        }
        
        (0..yAxisRange).forEach { i ->
            val y = calculateGridLineY(i, yAxisRange)
            
            drawLine(
                color = gridColor,
                start = Offset(xStart, y),
                end = Offset(xEnd, y),
                strokeWidth = ChartDimensions.GRID_LINE_WIDTH.toPx(),
                pathEffect = pathEffect
            )
        }
    }
    
    /**
     * Calculates the Y position for a grid line.
     */
    private fun DrawScope.calculateGridLineY(index: Int, yAxisRange: Int): Float {
        val baseY = size.height.toDp().toPx() - 
            ChartDimensions.SPACING_Y.toPx() - 
            index * (size.height.toDp() - ChartDimensions.SPACING_Y).toPx() / yAxisRange
        
        return baseY + ChartDimensions.GRID_Y_ALIGNMENT.toPx()
    }
}
