package com.moneyplusplus.presentation.statistics

import com.moneyplusplus.design_system.component.chart.data.ChartPoint
import com.moneyplusplus.domain.entity.MonthlyOverview
import com.moneyplusplus.presentation.base.UiEffect
import com.moneyplusplus.presentation.base.UiIntent
import com.moneyplusplus.presentation.base.UiState

data class StatisticsState(
    val isLoading: Boolean = false,
    val selectedMonth: String = "",
    val monthlyOverview: MonthlyOverview? = null,
    val spendingTrendPoints: List<ChartPoint> = emptyList(),
    val currency: String = "",
) : UiState

sealed interface StatisticsIntent : UiIntent {
    data object LoadData : StatisticsIntent
    data class MonthSelected(val month: String) : StatisticsIntent
}

sealed interface StatisticsEffect : UiEffect
