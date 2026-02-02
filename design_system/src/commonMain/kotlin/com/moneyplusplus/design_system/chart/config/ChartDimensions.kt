package com.moneyplusplus.design_system.chart.config

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Dimension configuration for the chart
 */
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

        val Compact = ChartDimensions(
            lineWidth = 2f,
            pointRadius = 4f,
            chartHeight = 150.dp
        )

        val Large = ChartDimensions(
            lineWidth = 4f,
            pointRadius = 8f,
            chartHeight = 300.dp
        )
    }
}