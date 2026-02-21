package com.moneyplusplus.presentation.statistics.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.domain.entity.MonthlyOverview
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.expense
import money.presentation.generated.resources.ic_money_remove
import money.presentation.generated.resources.ic_wallet_add
import money.presentation.generated.resources.income
import money.presentation.generated.resources.monthly_overview
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private val ContentPadding = 16.dp
private val ContentCornerRadius = 12.dp
private val TitleBottomSpacing = 16.dp
private val SummarySpacing = 24.dp
private val BarTopSpacing = 24.dp
private val ScaleTopSpacing = 16.dp

@Composable
internal fun MonthlyOverviewContent(
    overview: MonthlyOverview,
    modifier: Modifier = Modifier,
) {
    val contentShape = remember { RoundedCornerShape(ContentCornerRadius) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(contentShape)
            .background(Theme.colorScheme.surface.surfaceLow)
            .padding(ContentPadding),
    ) {
        Text(
            text = stringResource(Res.string.monthly_overview),
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.title,
        )

        Spacer(modifier = Modifier.height(TitleBottomSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            MonthlyOverviewSummaryItem(
                icon = painterResource(Res.drawable.ic_wallet_add),
                iconColor = Theme.colorScheme.secondary.secondary,
                label = stringResource(Res.string.income),
                value = overview.income,
                currency = overview.currency,
                isIncome = true,
                modifier = Modifier.weight(1f),
            )

            Spacer(modifier = Modifier.width(SummarySpacing))

            MonthlyOverviewSummaryItem(
                icon = painterResource(Res.drawable.ic_money_remove),
                iconColor = Theme.colorScheme.primary.primary,
                label = stringResource(Res.string.expense),
                value = overview.expenses,
                currency = overview.currency,
                isIncome = false,
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(BarTopSpacing))

        MonthlyOverviewProgressBar(overview = overview)

        Spacer(modifier = Modifier.height(ScaleTopSpacing))

        MonthlyOverviewScaleLabels(labels = overview.scaleLabels)
    }
}
