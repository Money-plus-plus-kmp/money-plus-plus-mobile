package com.moneyplusplus.design_system.chart.models

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ChartDimensions(
    val lineWidth: Float = 2f,
    val pointRadius: Float = 6f,
    val pointBorderWidth: Float = 2f,
    val gridLineWidth: Float = 1f,
    val horizontalPadding: Dp = 0.dp,
    val verticalPadding: Dp = 20.dp,
    val axisLabelSize: TextUnit = 12.sp,
    val titleSize: TextUnit = 18.sp,
    val chartHeight: Dp = 200.dp
) {
    companion object {
        val Default = ChartDimensions()
    }
}