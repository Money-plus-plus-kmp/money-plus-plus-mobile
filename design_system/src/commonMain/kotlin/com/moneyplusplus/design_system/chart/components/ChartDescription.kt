package com.moneyplusplus.design_system.chart.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.moneyplusplus.design_system.component.text.Text

@Composable
internal fun ChartDescription(
    chartName: String,
    descriptionStyle: TextStyle,
) {
    Text(
        text = chartName,
        modifier = Modifier,
        style = descriptionStyle
    )
}
