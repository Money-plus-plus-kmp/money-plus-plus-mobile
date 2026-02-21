package com.moneyplusplus.presentation.statistics.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moneyplusplus.design_system.component.empty.SectionEmptyView
import com.moneyplusplus.domain.entity.MonthlyOverview
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.monthly_overview
import money.presentation.generated.resources.no_monthly_overview
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MonthlyOverviewSection(
    overview: MonthlyOverview,
    modifier: Modifier = Modifier,
) {
    if (overview.isEmpty) {
        SectionEmptyView(
            title = stringResource(Res.string.monthly_overview),
            message = stringResource(Res.string.no_monthly_overview),
            modifier = modifier,
        )
        return
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        MonthlyOverviewContent(overview = overview)

        if (overview.hasSavings) {
            MonthlyOverviewSavingsBanner(
                savings = overview.savings,
                currency = overview.currency,
            )
        }
    }
}
