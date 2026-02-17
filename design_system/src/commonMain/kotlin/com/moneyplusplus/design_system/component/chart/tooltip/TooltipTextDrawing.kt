package com.moneyplusplus.design_system.component.chart.tooltip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText

@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.drawTooltipText(
    textMeasurer: TextMeasurer,
    text: String,
    textStyle: TextStyle,
    position: Offset
) {
    drawText(
        textMeasurer = textMeasurer,
        text = text,
        style = textStyle,
        topLeft = position
    )
}
