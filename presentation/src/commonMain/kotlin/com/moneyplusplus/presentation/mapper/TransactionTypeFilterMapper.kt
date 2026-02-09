package com.moneyplusplus.presentation.mapper

import com.moneyplusplus.domain.entity.TransactionType
import com.moneyplusplus.presentation.feature.transaction.TransactionUiState

fun TransactionUiState.TransactionTypeFilter.toDomain() = when (this) {
    TransactionUiState.TransactionTypeFilter.ALL -> null
    TransactionUiState.TransactionTypeFilter.INCOMES -> TransactionType.INCOME
    TransactionUiState.TransactionTypeFilter.EXPENSES -> TransactionType.EXPENSE
}