package com.moneyplusplus.design_system.chart.config

import androidx.compose.ui.graphics.Color

data class ChartColorScheme(
    val lineColor: Color,
    val gridColor: Color = Color.Gray.copy(alpha = 0.3f),
    val axisLabelColor: Color = Color.Gray
) {
    companion object {
        val RED = ChartColorScheme(
            lineColor = Color(0xFFE53935)
        )
    }
}