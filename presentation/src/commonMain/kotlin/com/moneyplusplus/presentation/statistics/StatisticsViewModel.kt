package com.moneyplusplus.presentation.statistics

import com.moneyplusplus.design_system.component.chart.data.ChartPoint
import com.moneyplusplus.domain.entity.MonthlyOverview
import com.moneyplusplus.domain.repository.StatisticsRepository
import com.moneyplusplus.presentation.base.BaseViewModel
import kotlinx.datetime.LocalDate
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
class StatisticsViewModel(
    @Provided val statisticsRepository: StatisticsRepository,
) : BaseViewModel<StatisticsState, StatisticsIntent, StatisticsEffect>(StatisticsState()) {

    init {
        handleIntent(StatisticsIntent.LoadData)
    }

    override fun handleIntent(intent: StatisticsIntent) {
        when (intent) {
            StatisticsIntent.LoadData -> loadStatistics()
            is StatisticsIntent.MonthSelected -> onMonthSelected(intent.month)
        }
    }

    private fun loadStatistics() {
        tryExecute(
            onStart = ::setLoadingState,
            block = ::fetchStatistics,
            onSuccess = ::handleSuccess,
            onError = ::handleError,
        )
    }

    private suspend fun fetchStatistics(): FetchResult {
        val month = LocalDate(2025, 12, 1)
        val overview = statisticsRepository.getMonthlyOverview(month)

        return FetchResult(
            selectedMonth = "December, 2025",
            overview = overview,
            currency = overview?.currency ?: "IQD",
            spendingTrendPoints = emptyList(), // from repository
        )
    }

    private fun handleSuccess(result: FetchResult) {
        updateState {
            copy(
                isLoading = false,
                selectedMonth = result.selectedMonth,
                monthlyOverview = result.overview,
                currency = result.currency,
                spendingTrendPoints = result.spendingTrendPoints,
            )
        }
    }

    private fun handleError(error: Throwable) {
        updateState { copy(isLoading = false) }
    }

    private fun onMonthSelected(month: String) {
        updateState { copy(selectedMonth = month) }
        loadStatistics()
    }

    private fun setLoadingState() {
        updateState { copy(isLoading = true) }
    }
}

private data class FetchResult(
    val selectedMonth: String,
    val overview: MonthlyOverview?,
    val currency: String,
    val spendingTrendPoints: List<ChartPoint>,
)
