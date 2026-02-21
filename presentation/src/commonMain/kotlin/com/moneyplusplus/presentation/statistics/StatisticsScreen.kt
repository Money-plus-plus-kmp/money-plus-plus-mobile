package com.moneyplusplus.presentation.statistics

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.domain.entity.MonthlyOverview
import com.moneyplusplus.presentation.statistics.component.MonthlyOverviewSection
import com.moneyplusplus.presentation.statistics.component.SpendingTrendChart
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

private val ContentPadding = 16.dp
private val TopSpacing = 50.dp
private val SectionSpacing = 24.dp

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    StatisticsScreenContent(
        state = state,
        intent = viewModel::handleIntent,
    )
}

@Composable
private fun StatisticsScreenContent(
    state: StatisticsState,
    intent: (StatisticsIntent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.padding(ContentPadding)
    ) {
        Spacer(modifier = Modifier.height(TopSpacing))

        state.monthlyOverview?.let { overview ->
            MonthlyOverviewSection(overview = overview)
        }

        Spacer(modifier = Modifier.height(SectionSpacing))

        SpendingTrendChart(
            title = "Spending Trend",
            points = state.spendingTrendPoints,
            valueSuffix = state.currency,
        )
    }
}

@Preview
@Composable
private fun StatisticsScreenPreview() {
    MoneyTheme {
        StatisticsScreenContent(
            state = StatisticsState(
                monthlyOverview = MonthlyOverview(
                    income = 1_500_000.0,
                    expenses = 850_000.0,
                    currency = "IQD",
                    maxValue = 2_000_000.0,
                    scaleLabels = listOf(
                        "0", "250K", "500K", "750K",
                        "1M", "1.25M", "1.5M", "1.75M", "2M",
                    ),
                ),
                currency = "IQD",
            ),
            intent = {},
        )
    }
}
