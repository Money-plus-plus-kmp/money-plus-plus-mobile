package com.moneyplusplus.design_system.chart.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneyplusplus.design_system.chart.models.LineParameters

internal object ChartDefaultValues {

    val lineParameters: List<LineParameters> = listOf(
        LineParameters(
            label = "revenue",
            data = emptyList(),
            lineColor = Color.Blue,
            lineShadow = true,
        )
    )

    val barWidth = 30.dp
    val spaceBetweenBars = 10.dp
    val spaceBetweenGroups = 40.dp
    val gridColor = Color.Gray
    const val ANIMATED_CHART = true
    val backgroundLineWidth = 1.dp
    const val SHOW_BACKGROUND_WITH_SPACER = true
    const val chartRatio = 0f
    val descriptionDefaultStyle = TextStyle(
        color = Color.Black,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400
    )

    val headerArrangement = Arrangement.spacedBy(24.dp)
    val axesStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.Gray,
    )
    const val yAxisRange = 6
    val barCornerRadius = 0.dp
}
