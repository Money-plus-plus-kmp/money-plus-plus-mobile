package com.moneyplusplus.design_system.chart.components.grid

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.chart.utils.ChartConstants
import com.moneyplusplus.design_system.chart.utils.formatToThousandsMillionsBillions

@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.grid(
    gridColor: Color,
    backgroundLineWidth: Float,
    showGridWithSpacer: Boolean,
    spacingY: Dp,
    yAxisRange: Int,
    upperValue: Float,
    textMeasurer: TextMeasurer,
) {
    val yMaxTextWidth = textMeasurer.measure(
        text = AnnotatedString(upperValue.formatToThousandsMillionsBillions()),
    ).size.width
    val textSpace = yMaxTextWidth - (yMaxTextWidth/4)
    val xAxisMaxValue = size.width

    (0..yAxisRange).forEach { i ->
        val y = size.height.toDp().toPx() - (ChartConstants.spacingY.toPx()) - i * (size.height.toDp() - ChartConstants.spacingY).toPx() / yAxisRange
        val yAlignmentValue = y + ChartConstants.gridYAlignment.toPx()

        val xStart = (textSpace.toDp() + ChartConstants.textSpacing).toPx()
        val xEnd = xAxisMaxValue - (textSpace/0.9.toFloat().toDp().toPx())

        drawLine(
            color = gridColor,
            start = Offset(xStart, yAlignmentValue),
            end = Offset(xEnd, yAlignmentValue),
            strokeWidth = backgroundLineWidth,
            pathEffect = PathEffect.dashPathEffect(
                if (showGridWithSpacer) floatArrayOf(ChartConstants.gridDashInterval, ChartConstants.gridDashInterval)
                else floatArrayOf(1f, 1f),
                0f
            )
        )
    }
}
