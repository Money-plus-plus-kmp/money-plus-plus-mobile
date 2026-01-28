package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.presentation.base.UiState
import com.moneyplusplus.presentation.model.CategoryUiModel
import com.moneyplusplus.presentation.model.TransactionUiModel
import kotlinx.datetime.LocalDate

data class TransactionUiState(
    val transactions: List<TransactionUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val typeFilter: TransactionTypeFilter = TransactionTypeFilter.ALL,
    val date: LocalDate? = null,
    val showCategoriesFilterBottomSheet: Boolean = false,
    val showAddTransactionBottomSheet: Boolean = false,
    val categories: List<CategoryUiModel> = emptyList(),
    val selectedCategoryIds: List<String> = emptyList(),
    val showDatePickerDialog: Boolean = false
) : UiState