package com.moneyplusplus.design_system.component.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.core.ChartContainer
import com.moneyplusplus.design_system.component.text.Text

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<Double>,
    title: String,
    valueSuffix: String?,
    xAxisLabels: List<String> = emptyList(),
    config: ChartConfig = ChartConfig.defaults()
) {
    Box(modifier.wrapContentHeight()) {
        Column {
            if (title.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(bottom = TITLE_BOTTOM_PADDING),
                    text = title,
                    style = config.styles.title,
                    color = config.colors.axisLabelColor
                )
            }
            ChartContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CHART_HEIGHT),
                data = data,
                config = config,
                valueSuffix = valueSuffix,
                xAxisLabels = xAxisLabels
            )
        }
    }
}

private val TITLE_BOTTOM_PADDING = 16.dp
private val CHART_HEIGHT = 200.dp