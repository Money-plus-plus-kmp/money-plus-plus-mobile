package com.moneyplusplus.design_system.chart.config

import androidx.compose.ui.graphics.Color

/**
 * Color configuration for the chart
 */
data class ChartColors(
    val lineColor: Color = Color(0xFFDC143C),
    val gradientStart: Color = Color(0xFFDC143C).copy(alpha = 0.3f),
    val gradientEnd: Color = Color(0xFFDC143C).copy(alpha = 0.0f),
    val gridColor: Color = Color(0xFFE0E0E0),
    val axisLabelColor: Color = Color(0xFF9E9E9E),
    val tooltipBackground: Color = Color(0xFFFFFFFF),
    val tooltipTextColor: Color = Color(0xFF333333),
    val highlightPointColor: Color = Color(0xFFDC143C),
    val highlightPointBorderColor: Color = Color.White
) {
    companion object {
        val Default = ChartColors()

        val Blue = ChartColors(
            lineColor = Color(0xFF2196F3),
            gradientStart = Color(0xFF2196F3).copy(alpha = 0.3f),
            gradientEnd = Color(0xFF2196F3).copy(alpha = 0.0f)
        )
    }
}