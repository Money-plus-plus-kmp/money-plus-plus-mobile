package com.moneyplusplus.design_system.component.chart.utils

internal fun checkIfDataValid(
    xAxisData: List<String>,
    data: List<Double>
) {
    if (data.size != xAxisData.size) {
        throw Exception("The data size of line must be equal to the x-axis data size.")
    }
    checkIfDataIsNegative(data)
}

internal fun checkIfDataIsNegative(data: List<Double>) {
    data.forEach {
        if (it < 0.0) {
            throw Exception("The data can't contains negative values.")
        }
    }
}
