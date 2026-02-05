package com.moneyplusplus.design_system.component.chart.core.axis

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.drawYAxisLabels(
    upperValue: Float,
    lowerValue: Float,
    textMeasurer: TextMeasurer,
    xAxisLabelsHeight: Float,
    textStyle: TextStyle,
    numberOfLabels: Int
) {
    val dataRange = upperValue - lowerValue
    val valueStep = if (numberOfLabels > 0) dataRange / numberOfLabels else 0f
    val chartDrawableHeight = size.height - xAxisLabelsHeight

    (0..numberOfLabels).forEach { labelIndex ->
        val labelValue = lowerValue + (valueStep * labelIndex)
        val formattedValue = NumberFormatter.formatCompactNumber(labelValue)

        val yPosition = size.height - xAxisLabelsHeight - (labelIndex * chartDrawableHeight / numberOfLabels)

        drawText(
            textMeasurer = textMeasurer,
            text = formattedValue,
            style = textStyle,
            topLeft = Offset(0f, yPosition)
        )
    }
}
