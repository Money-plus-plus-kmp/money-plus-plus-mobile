package com.moneyplusplus.design_system.component.chart.core.axis

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

/**
 * Renders the Y-axis labels along the left side of the chart.
 */
internal object YAxisRenderer {
    
    /**
     * Draws Y-axis labels at each interval.
     *
     * @param upperValue The maximum value on the Y-axis
     * @param lowerValue The minimum value on the Y-axis
     * @param textMeasurer The text measurer for drawing text
     * @param spacing The bottom spacing (for X-axis labels)
     * @param textStyle The style for the labels
     * @param yAxisRange The number of intervals on the Y-axis
     */
    @OptIn(ExperimentalTextApi::class)
    fun DrawScope.drawYAxisLabels(
        upperValue: Float,
        lowerValue: Float,
        textMeasurer: TextMeasurer,
        spacing: Dp,
        textStyle: TextStyle,
        yAxisRange: Int
    ) {
        val dataRange = upperValue - lowerValue
        val dataStep = if (yAxisRange > 0) dataRange / yAxisRange else 0f
        
        (0..yAxisRange).forEach { i ->
            val yValue = lowerValue + (dataStep * i)
            val formattedValue = NumberFormatter.formatCompactNumber(yValue)
            
            val y = calculateLabelY(i, yAxisRange, spacing)
            
            drawText(
                textMeasurer = textMeasurer,
                text = formattedValue,
                style = textStyle,
                topLeft = Offset(0f, y.toPx())
            )
        }
    }
    
    /**
     * Calculates the Y position for a label.
     */
    private fun DrawScope.calculateLabelY(index: Int, yAxisRange: Int, spacing: Dp): Dp {
        val totalHeight = size.height.toDp()
        val availableHeight = totalHeight - spacing
        val yOffset = availableHeight * index / yAxisRange
        
        return totalHeight - spacing - yOffset
    }
}
