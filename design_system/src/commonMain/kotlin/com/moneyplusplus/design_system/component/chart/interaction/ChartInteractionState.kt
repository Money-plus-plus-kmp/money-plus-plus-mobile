package com.moneyplusplus.design_system.component.chart.interaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Manages the interaction state for chart point selection.
 * Replaces the module-level mutable var pattern with proper state management.
 */
internal class ChartInteractionState {
    
    /**
     * The index of the currently selected point, or null if none selected.
     */
    var selectedPointIndex: Int? by mutableStateOf(null)
        private set
    
    /**
     * Toggles selection of a point.
     * If the same point is tapped again, it's deselected.
     *
     * @param index The index of the tapped point
     */
    fun togglePointSelection(index: Int) {
        selectedPointIndex = if (selectedPointIndex == index) null else index
    }
    
    /**
     * Selects a specific point.
     *
     * @param index The index of the point to select
     */
    fun selectPoint(index: Int) {
        selectedPointIndex = index
    }
    
    /**
     * Clears the current selection.
     */
    fun clearSelection() {
        selectedPointIndex = null
    }
    
    /**
     * Checks if a specific point is selected.
     *
     * @param index The index to check
     * @return True if the point at this index is selected
     */
    fun isPointSelected(index: Int): Boolean = selectedPointIndex == index
}
