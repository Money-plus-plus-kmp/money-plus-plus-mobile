package com.moneyplusplus.design_system.chart.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
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
        val y = size.height.toDp().toPx() - (spacingY.toPx()) - i * (size.height.toDp() - spacingY).toPx() / yAxisRange
        val yAlignmentValue = y + 9.dp.toPx()

        val xStart = (textSpace.toDp() + 10.dp).toPx()
        val xEnd = xAxisMaxValue - (textSpace/0.9.toFloat().toDp().toPx())

        drawLine(
            color = gridColor,
            start = Offset(xStart, yAlignmentValue),
            end = Offset(xEnd, yAlignmentValue),
            strokeWidth = backgroundLineWidth,
            pathEffect = PathEffect.dashPathEffect(
                if (showGridWithSpacer) floatArrayOf(16f, 16f)
                else floatArrayOf(1f, 1f),
                0f
            )
        )
    }
}
