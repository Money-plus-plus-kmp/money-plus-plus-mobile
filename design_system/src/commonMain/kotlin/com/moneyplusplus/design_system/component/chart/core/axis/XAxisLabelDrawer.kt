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
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.calculation.ChartLayoutCalculator
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

@OptIn(ExperimentalTextApi::class)
internal fun <T> DrawScope.drawXAxisLabels(
    labels: List<T>,
    textMeasurer: TextMeasurer,
    textStyle: TextStyle,
    upperValue: Float,
    xRegionWidth: Dp
) {
    val yTextLayoutResult = textMeasurer.measure(
        text = AnnotatedString(NumberFormatter.formatCompactNumber(upperValue))
    ).size.width

    val yAxisLabelsWidth = ChartLayoutCalculator.calculateYAxisLabelsWidth(yTextLayoutResult)
    val startOffset = yAxisLabelsWidth.toDp() + ChartDimensions.TEXT_SPACING
    val labelBottomOffset = 20.dp
    val labelY = size.height - labelBottomOffset.toPx()

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

private val X_AXIS_LABEL_BOTTOM_OFFSET = 20.dp
