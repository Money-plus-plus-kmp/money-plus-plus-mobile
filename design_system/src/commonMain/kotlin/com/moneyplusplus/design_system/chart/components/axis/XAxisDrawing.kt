package com.moneyplusplus.design_system.chart.components.axis

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
import androidx.compose.ui.unit.times
import com.moneyplusplus.design_system.chart.utils.formatToThousandsMillionsBillions

@OptIn(ExperimentalTextApi::class)
internal fun <T> DrawScope.xAxisDrawing(
    xAxisData: List<T>,
    textMeasure: TextMeasurer,
    xAxisStyle: TextStyle,
    upperValue: Float,
    xRegionWidth: Dp
) {
    val yTextLayoutResult = textMeasure.measure(
        text = AnnotatedString(upperValue.formatToThousandsMillionsBillions()),
    ).size.width
    val textSpace = yTextLayoutResult - (yTextLayoutResult/4)

    xAxisData.forEachIndexed { index, dataPoint ->
        val xLength = (textSpace.toDp() + 10.dp) + (index * xRegionWidth)

        drawText(
            textMeasurer = textMeasure,
            text = dataPoint.toString(),
            style = xAxisStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            topLeft = Offset(
                xLength.coerceAtMost(size.width.toDp()).toPx(),
                size.height - 20.dp.toPx()
            )
        )
    }
}
