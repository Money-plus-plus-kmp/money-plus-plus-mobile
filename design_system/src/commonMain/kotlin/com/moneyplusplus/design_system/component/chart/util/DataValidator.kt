package com.moneyplusplus.design_system.component.chart.util


internal fun validateChartData(
    data: List<Double>,
    xAxisLabels: List<String>
) {
    require(data.size == xAxisLabels.size) {
        "The data size (${data.size}) must equal the x-axis labels size (${xAxisLabels.size})."
    }
    validateNoNegativeValues(data)
}

private fun validateNoNegativeValues(data: List<Double>) {
    val negativeIndex = data.indexOfFirst { it < 0.0 }
    require(negativeIndex < 0) {
        "Chart data cannot contain negative values. Found ${data[negativeIndex]} at index $negativeIndex."
    }
}