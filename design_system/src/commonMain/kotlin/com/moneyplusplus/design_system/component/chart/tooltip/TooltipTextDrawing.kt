package com.moneyplusplus.design_system.component.chart.tooltip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import com.moneyplusplus.design_system.component.chart.constants.TooltipConstants

/**
 * Draws the tooltip text content.
 */
@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.drawTooltipText(
    textMeasurer: TextMeasurer,
    text: String,
    textColor: Color,
    position: Offset
) {
    val textStyle = TextStyle(
        fontSize = TooltipConstants.FONT_SIZE,
        color = textColor
    )
    
    drawText(
        textMeasurer = textMeasurer,
        text = text,
        style = textStyle,
        topLeft = position
    )
}
