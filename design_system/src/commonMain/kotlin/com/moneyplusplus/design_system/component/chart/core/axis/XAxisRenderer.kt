package com.moneyplusplus.design_system.component.chart.core.axis

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.calculation.ChartBoundsCalculator
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

/**
 * Renders the X-axis labels along the bottom of the chart.
 */
internal object XAxisRenderer {
    
    /**
     * Draws X-axis labels at each data point position.
     *
     * @param labels The list of labels to display
     * @param textMeasurer The text measurer for drawing text
     * @param textStyle The style for the labels
     * @param upperValue The upper Y-axis value (for calculating text space)
     * @param xRegionWidth The width of each X region
     */
    @OptIn(ExperimentalTextApi::class)
    fun <T> DrawScope.drawXAxisLabels(
        labels: List<T>,
        textMeasurer: TextMeasurer,
        textStyle: TextStyle,
        upperValue: Float,
        xRegionWidth: Dp
    ) {
        val yTextLayoutResult = textMeasurer.measure(
            text = AnnotatedString(NumberFormatter.formatCompactNumber(upperValue))
        ).size.width
        
        val textSpace = ChartBoundsCalculator.calculateTextSpace(yTextLayoutResult)
        val startOffset = textSpace.toDp() + ChartDimensions.TEXT_SPACING
        val labelY = size.height - ChartDimensions.X_AXIS_LABEL_BOTTOM_OFFSET.toPx()
        
        labels.forEachIndexed { index, label ->
            val xPosition = (startOffset + (xRegionWidth * index))
                .coerceAtMost(size.width.toDp())
                .toPx()
            
            drawText(
                textMeasurer = textMeasurer,
                text = label.toString(),
                style = textStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                topLeft = Offset(xPosition, labelY)
            )
        }
    }
}
