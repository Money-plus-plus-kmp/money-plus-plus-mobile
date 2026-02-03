package com.moneyplusplus.design_system.component.chart.utils

import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun List<Double>.getUpperValue(): Double {
    return this.maxOrNull()?.plus(1.0) ?: 0.0
}

fun List<Double>.getLowerValue(): Double {
    return this.minOrNull() ?: 0.0
}

fun CoroutineScope.collectToSnapShotFlow(
    data: List<Double>,
    makeUpdateData: (List<Double>) -> Unit,
) {
    this.launch {
        snapshotFlow {
            data
        }.collect {
            makeUpdateData(it)
        }
    }
}
