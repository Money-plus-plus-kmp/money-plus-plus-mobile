package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.domain.model.TransactionFilter
import com.moneyplusplus.domain.usecase.GetCategoriesUseCase
import com.moneyplusplus.domain.usecase.transaction.GetTransactionsUseCase
import com.moneyplusplus.presentation.base.BaseViewModel
import com.moneyplusplus.presentation.mapper.toUiModel
import com.moneyplusplus.presentation.model.TransactionUiModel


class TransactionViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : BaseViewModel<
        TransactionUiState,
        TransactionIntent,
        TransactionEffect>(
    TransactionUiState()
) {
    init {
        loadTransactions()
        loadCategories()
    }

    override fun handleIntent(intent: TransactionIntent) {
        when (intent) {
            is TransactionIntent.OnFilterClick -> {
                updateState { copy(showCategoriesFilterBottomSheet = true) }
            }

            is TransactionIntent.OnFilterSheetDismissed -> {
                updateState { copy(showCategoriesFilterBottomSheet = false) }
            }

            is TransactionIntent.OnApplyFilterClick -> {
                updateState { copy(selectedCategoryIds = intent.selectedCategoryIds) }
                applyLocalFilters(newCategoryIds = intent.selectedCategoryIds)
            }

            is TransactionIntent.OnTransactionTypeClick -> {
                if (currentState.typeFilter == intent.type) return
                updateState { copy(typeFilter = intent.type) }
                applyLocalFilters(newTypeFilter = intent.type)
            }

            is TransactionIntent.OnDateClick -> {
                updateState { copy(showDatePickerDialog = true) }
            }

            is TransactionIntent.OnDatePickerDialogDismiss -> {
                updateState { copy(showDatePickerDialog = false) }
            }


            is TransactionIntent.OnDateSelected -> {
                updateState { copy(date = intent.date, showDatePickerDialog = false) }
                loadTransactions()
            }

            is TransactionIntent.OnAddTransactionClick -> {
                updateState { copy(showAddTransactionBottomSheet = true) }
            }

            is TransactionIntent.OnAddTransactionSheetDismissed -> {
                updateState { copy(showAddTransactionBottomSheet = false) }
            }

        }

    }

    private fun loadTransactions() {
        val filter = TransactionFilter(
            date = currentState.date,
            type = null,
            categoriesIds = emptyList()
        )
        tryExecute(
            block = { getTransactionsUseCase(filter) },
            onStart = {
                updateState { copy(isLoading = true, isError = false) }
            },
            onSuccess = { transactions ->
                val allTransactions = transactions.map { it.toUiModel() }
                updateState {
                    copy(
                        allTransactions = allTransactions,
                        isLoading = false,
                        isError = false
                    )
                }
                applyLocalFilters(newListToFilter = allTransactions)
            },
            onError = { error ->
                updateState {
                    copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = error.message
                    )
                }
                sendEffect(TransactionEffect.ShowSnackbarError(error.message ?: "Unknown Error"))
            }
        )
    }

    private fun loadCategories() {
        tryExecute(
            block = { getCategoriesUseCase() },
            onSuccess = { categories ->
                updateState {
                    copy(
                        categories = categories.map { category ->
                            category.toUiModel()
                        },
                    )
                }
            },
            onError = { error ->
                sendEffect(TransactionEffect.ShowSnackbarError(error.message ?: "Unknown Error"))
            }
        )

    }

    private fun applyLocalFilters(
        newListToFilter: List<TransactionUiModel>? = null,
        newTypeFilter: TransactionUiState.TransactionTypeFilter? = null,
        newCategoryIds: List<String>? = null
    ) {
        val typeFilter = newTypeFilter ?: currentState.typeFilter
        val selectedCategoryIds = newCategoryIds ?: currentState.selectedCategoryIds
        val allTransactions = newListToFilter ?: currentState.allTransactions
        var filteredTransactions = when (typeFilter) {
            TransactionUiState.TransactionTypeFilter.ALL -> allTransactions
            TransactionUiState.TransactionTypeFilter.INCOMES -> allTransactions.filter { it.isExpense.not() }
            TransactionUiState.TransactionTypeFilter.EXPENSES -> allTransactions.filter { it.isExpense }
        }

        if (selectedCategoryIds.isNotEmpty()) {
            filteredTransactions = filteredTransactions.filter { transaction ->
                transaction.categoryId in selectedCategoryIds
            }
        }
        updateState { copy(transactions = filteredTransactions) }
    }
}