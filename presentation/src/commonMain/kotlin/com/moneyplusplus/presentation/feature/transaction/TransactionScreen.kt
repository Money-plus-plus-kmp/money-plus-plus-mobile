package com.moneyplusplus.presentation.feature.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.chip.Chip
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.model.TransactionUiModel
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_down
import money.presentation.generated.resources.filter
import money.presentation.generated.resources.ic_arrow_down
import money.presentation.generated.resources.ic_filter
import money.presentation.generated.resources.transaction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionViewModel = koinViewModel(),

    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TransactionEffect.ShowDatePicker -> {}
            }
        }
    }

    TransactionScreenContent(
        transactions = state.transactions,
        date = state.date,
        onFilterClick = { viewModel.handleIntent(TransactionIntent.OnFilterClick) },
        onDateClick = { viewModel.handleIntent(TransactionIntent.OnDateClick) },
        onTransactionTypeClick = { transactionType ->
            viewModel.handleIntent(TransactionIntent.OnTransactionTypeClick(transactionType))
        },
        selectedType = state.typeFilter,
        modifier = modifier
    )
}

@Composable
private fun TransactionScreenContent(
    transactions: List<TransactionUiModel>,
    date: String,
    onFilterClick: () -> Unit,
    onDateClick: () -> Unit,
    onTransactionTypeClick: (TransactionTypeFilter) -> Unit,
    selectedType: TransactionTypeFilter,
    modifier: Modifier = Modifier,

    ) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TransactionTopAppBar(
                date = date,
                onFilterClick = onFilterClick,
                onDateClick = onDateClick

            )
        }
    ) {

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                TransactionTypeFilterSection(
                    onTransactionTypeClick = onTransactionTypeClick,
                    selectedType = selectedType
                )
            }
            if (transactions.isEmpty()) {
                item {
                    EmptyTransactionsView()
                }
            } else {
                items(
                    items = transactions,
                    key = { it.id }) { transaction ->
                    TransactionItem(
                        transaction = transaction
                    )
                }
            }

        }

    }
}

@Composable
fun EmptyTransactionsView() {
    Text("no data", style = Theme.typography.label.medium)
}

@Composable
private fun TransactionItem(
    transaction: TransactionUiModel,
    modifier: Modifier = Modifier
) {
    val signColor = if (transaction.isExpense)
        Theme.colorScheme.accent.red.red
    else
        Theme.colorScheme.accent.green.green
    val amountTextColor = Theme.colorScheme.title
    val amountText = remember(
        transaction.amount,
        transaction.isExpense,
        signColor,
        amountTextColor
    ) {
        buildAnnotatedString {
            withStyle(SpanStyle(color = signColor)) {
                append(if (transaction.isExpense) "-" else "+")
            }
            withStyle(
                style = SpanStyle(color = amountTextColor)
            ) {
                append(transaction.amount)
            }
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = transaction.title,
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(5f),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.weight(2f))
        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = amountText,
                style = Theme.typography.label.medium
            )

            Text(
                text = transaction.dateFormated,
                style = Theme.typography.label.small,
                color = Theme.colorScheme.hint
            )
        }
    }
}

@Composable
private fun TransactionTypeFilterSection(
    selectedType: TransactionTypeFilter,
    modifier: Modifier = Modifier,
    onTransactionTypeClick: (TransactionTypeFilter) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Theme.colorScheme.surface.surface,
            ),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TransactionTypeFilter.entries.forEach { type ->
            Chip(
                text = stringResource(type.labelResource),
                isSelected = type == selectedType,
                onClick = { onTransactionTypeClick(type) },
            )
        }

    }
}

@Composable
private fun TransactionTopAppBar(
    date: String,
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit = {},
    onDateClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.colorScheme.surface.surfaceLow)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.transaction),
            style = Theme.typography.title.small,
            color = Theme.colorScheme.title
        )
        Spacer(modifier = Modifier.weight(1f))
        DateSelector(
            date = date,
            onDateClick = onDateClick
        )
        Icon(
            painter = painterResource(Res.drawable.ic_filter),
            contentDescription = stringResource(Res.string.filter),
            modifier = Modifier
                .clip(CircleShape)
                .background(Theme.colorScheme.surface.surface)
                .clickable(onClick = onFilterClick)
                .padding(10.dp)
        )
    }
}

@Composable
private fun DateSelector(
    date: String,
    modifier: Modifier = Modifier,
    onDateClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(end = 8.dp)
            .clip(
                shape = RoundedCornerShape(100.dp)
            )
            .background(Theme.colorScheme.surface.surface)
            .clickable(onClick = onDateClick)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = date,
            style = Theme.typography.label.small,
            color = Theme.colorScheme.title
        )
        Icon(
            painter = painterResource(Res.drawable.ic_arrow_down),
            contentDescription = stringResource(Res.string.arrow_down),
        )
    }
}


@Preview
@Composable
fun TransactionScreenPreview() {
    MoneyTheme {
        TransactionScreenContent(
            transactions = TransactionFakeData.transactions,
            date = "January 2026",
            selectedType = TransactionTypeFilter.INCOMES,
            onFilterClick = {},
            onDateClick = {},
            onTransactionTypeClick = {}
        )
    }
}


////////////////////////////////////////////////////

object TransactionFakeData {

    val transactions = listOf(
        TransactionUiModel(
            id = "1",
            title = "Starbucks Coffee",
            amount = "45.50 USD",
            dateFormated = "26 Jan 2026",
            isExpense = true
        ),
        TransactionUiModel(
            id = "2",
            title = "Salary January",
            amount = "12,000.00 USD",
            dateFormated = "25 Jan 2026",
            isExpense = false
        ),
        TransactionUiModel(
            id = "3",
            title = "Uber Ride Downtown",
            amount = "120.75 USD",
            dateFormated = "24 Jan 2026",
            isExpense = true
        ),
        TransactionUiModel(
            id = "4",
            title = "Freelance Payment – Mobile App UI Design",
            amount = "3,400.00 USD",
            dateFormated = "23 Jan 2026",
            isExpense = false
        ),
        TransactionUiModel(
            id = "5",
            title = "Netflix Subscription",
            amount = "15.99 USD",
            dateFormated = "22 Jan 2026",
            isExpense = true
        ),
        TransactionUiModel(
            id = "6",
            title = "Grocery Shopping (Carrefour)",
            amount = "860.30 EGP",
            dateFormated = "21 Jan 2026",
            isExpense = true
        ),
        TransactionUiModel(
            id = "7",
            title = "Stock Dividends",
            amount = "540.00 USD",
            dateFormated = "20 Jan 2026",
            isExpense = false
        ),
        TransactionUiModel(
            id = "8",
            title = "Very Long Transaction Title That Should Be Ellipsized In The UI",
            amount = "75.00 USD",
            dateFormated = "19 Jan 2026",
            isExpense = true
        ),
        TransactionUiModel(
            id = "9",
            title = "Gym Membership",
            amount = "300.00 EGP",
            dateFormated = "18 Jan 2026",
            isExpense = true
        ),
        TransactionUiModel(
            id = "10",
            title = "Bonus Payment",
            amount = "2,000.00 USD",
            dateFormated = "17 Jan 2026",
            isExpense = false
        ),
    )
}
