package com.moneyplusplus.design_system.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.chart.data.ChartPoint
import com.moneyplusplus.design_system.chart.models.ChartConfig
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.design_system.chart.utils.calculateXAxisData

@Composable
fun SpendingTrendChart(
    points: List<ChartPoint>,
    title: String,
    modifier: Modifier = Modifier,
    valueSuffix: String?
) {
    if (points.isEmpty()) return

    val xAxisData = points.calculateXAxisData()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(238.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Theme.colorScheme.surface.surfaceLow)
            .padding(16.dp)
    ) {
        val config = ChartConfig.defaults()

        LineChart(
            modifier = Modifier.fillMaxSize(),
            data = points.map { it.value },
            title = title,
            config = config,
            valueSuffix = valueSuffix,
            xAxisData = xAxisData
        )
    }
}
