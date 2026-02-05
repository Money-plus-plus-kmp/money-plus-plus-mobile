package com.moneyplusplus.design_system.component.chart.util

/**
 * Validates chart data before rendering.
 */
internal object DataValidator {
    
    /**
     * Validates that the chart data is valid for rendering.
     *
     * @param data The Y-axis values
     * @param xAxisLabels The X-axis labels
     * @throws IllegalArgumentException if data is invalid
     */
    fun validateChartData(
        data: List<Double>,
        xAxisLabels: List<String>
    ) {
        require(data.size == xAxisLabels.size) {
            "The data size (${data.size}) must equal the x-axis labels size (${xAxisLabels.size})."
        }
        validateNoNegativeValues(data)
    }
    
    /**
     * Validates that no negative values exist in the data.
     *
     * @param data The data to validate
     * @throws IllegalArgumentException if any value is negative
     */
    fun validateNoNegativeValues(data: List<Double>) {
        val negativeIndex = data.indexOfFirst { it < 0.0 }
        require(negativeIndex < 0) {
            "Chart data cannot contain negative values. Found ${data[negativeIndex]} at index $negativeIndex."
        }
    }
    
    /**
     * Checks if data is valid without throwing exceptions.
     *
     * @param data The Y-axis values
     * @param xAxisLabels The X-axis labels
     * @return True if data is valid, false otherwise
     */
    fun isValidChartData(
        data: List<Double>,
        xAxisLabels: List<String>
    ): Boolean {
        return data.size == xAxisLabels.size && data.none { it < 0.0 }
    }
}
