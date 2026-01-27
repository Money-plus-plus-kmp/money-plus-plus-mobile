package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.presentation.base.UiState
import com.moneyplusplus.presentation.model.TransactionUiModel

data class TransactionUiState (
    val transactions: List<TransactionUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val typeFilter: TransactionTypeFilter = TransactionTypeFilter.ALL,
    val date: String = "",
    val showFilterBottomSheet: Boolean = false,
): UiState


