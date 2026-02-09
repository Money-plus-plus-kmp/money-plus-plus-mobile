package com.moneyplusplus.presentation.feature.transaction.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chip.Chip
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.presentation.feature.transaction.TransactionUiState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionTypeFilterSection(
    selectedTransactionType: TransactionUiState.TransactionTypeFilter,
    modifier: Modifier = Modifier,
    onTransactionTypeClick: (TransactionUiState.TransactionTypeFilter) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TransactionUiState.TransactionTypeFilter.entries.forEach { type ->
            Chip(
                text = stringResource(type.labelResource),
                isSelected = type == selectedTransactionType,
                onClick = { onTransactionTypeClick(type) },
            )
        }

    }
}

@Preview(heightDp = 50, showBackground = true)
@Composable
private fun TransactionTypeFilterSectionPreview() {
    MoneyTheme {
        TransactionTypeFilterSection(
            selectedTransactionType = TransactionUiState.TransactionTypeFilter.ALL,
            onTransactionTypeClick = {}
            )
    }
}