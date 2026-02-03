package com.moneyplusplus.design_system.component.chart

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.components.ChartContent
import com.moneyplusplus.design_system.component.chart.models.ChartConfig
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.chart.utils.ChartConstants

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<Double>,
    title: String,
    valueSuffix: String?,
    xAxisData: List<String> = emptyList(),
    config: ChartConfig = ChartConfig.defaults(),
) {
    val clickedPoints = remember { mutableStateListOf<Pair<Float, Float>>() }

    Box(modifier.wrapContentHeight()) {
        Column {

            if (title.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = title,
                    style = config.styles.title,
                    color = config.colors.axisLabelColor
                )
            }

            ChartContent(
                modifier = Modifier.fillMaxWidth().height(ChartConstants.chartHeight),
                data = data,
                config = config,
                valueSuffix = valueSuffix,
                xAxisData = xAxisData,
                onChartClick = { x, y ->
                    clickedPoints.add(x to y)
                },
                clickedPoints = clickedPoints,
            )

        }
    }
}
