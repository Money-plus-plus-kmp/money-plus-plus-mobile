package com.moneyplusplus.design_system.component.chart.interaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

internal class ChartInteractionState {

    var selectedPointIndex: Int? by mutableStateOf(null)
        private set

    fun togglePointSelection(index: Int) {
        selectedPointIndex = if (selectedPointIndex == index) null else index
    }

    fun clearSelection() {
        selectedPointIndex = null
    }
}
