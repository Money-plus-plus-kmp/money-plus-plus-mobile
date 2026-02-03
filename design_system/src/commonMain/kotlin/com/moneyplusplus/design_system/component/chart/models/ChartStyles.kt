package com.moneyplusplus.design_system.component.chart.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.moneyplusplus.design_system.theme.theme.Theme

data class ChartStyles(
    val axisLabel: TextStyle,
    val title: TextStyle
) {
    companion object {
        @Composable
        fun defaults(
            axisLabel: TextStyle = Theme.typography.label.xSmall.copy(color = Theme.colorScheme.hint),
            title: TextStyle = Theme.typography.label.medium.copy(color = Theme.colorScheme.hint)
        ): ChartStyles =
            ChartStyles(
                axisLabel = axisLabel,
                title = title
            )
    }
}
