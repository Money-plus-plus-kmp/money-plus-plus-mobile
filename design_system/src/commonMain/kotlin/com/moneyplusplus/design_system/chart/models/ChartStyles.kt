package com.moneyplusplus.design_system.chart.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.moneyplusplus.design_system.theme.theme.Theme

data class ChartStyles(
    val axisLabel: TextStyle,
    val description: TextStyle
) {
    companion object {
        @Composable
        fun defaults(
            axisLabel: TextStyle = Theme.typography.label.medium.copy(color = Theme.colorScheme.hint),
            description: TextStyle = Theme.typography.label.medium.copy(color = Theme.colorScheme.hint)
        ): ChartStyles = ChartStyles(
            axisLabel = axisLabel,
            description = description
        )
    }
}
