package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.domain.model.TransactionFilter
import com.moneyplusplus.domain.repository.CategoryRepository
import com.moneyplusplus.domain.usecase.transaction.GetTransactionsUseCase
import com.moneyplusplus.presentation.base.BaseViewModel
import com.moneyplusplus.presentation.mapper.toDomain
import com.moneyplusplus.presentation.mapper.toUiModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.uuid.Uuid


class TransactionViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val categoryRepository: CategoryRepository,
) : BaseViewModel<
        TransactionUiState,
        TransactionIntent,
        TransactionEffect>(
    TransactionUiState()
) {
    init {
        initDateThenLoadTransactions()
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
                applyLocalFilters()
            }

            is TransactionIntent.OnTransactionTypeClick -> {
                if (currentState.typeFilter == intent.type) return
                updateState { copy(typeFilter = intent.type) }
                applyLocalFilters()
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

    private fun initDateThenLoadTransactions() {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        updateState { copy(date = today) }
        loadTransactions()
    }


    private fun loadTransactions() {
        val filter = TransactionFilter(
            date = currentState.date,
            type = currentState.typeFilter.toDomain(),
            categoriesIds = currentState.selectedCategoryIds.map { Uuid.parse(it) }
        )
        tryExecute(
            block = { getTransactionsUseCase(filter) },
            onStart = {
                updateState { copy(isLoading = true, isError = false) }
            },
            onSuccess = { transactions ->
                updateState {
                    copy(
                        allTransactions = transactions.map { it.toUiModel() },
                        isLoading = false,
                        isError = false
                    )
                }
                applyLocalFilters()
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
            block = { categoryRepository.getCategories() },
            onStart = {
                updateState { copy(isLoading = true, isError = false) }
            },
            onSuccess = { categories ->
                updateState {
                    copy(
                        categories = categories.map { category ->
                            category.toUiModel()
                        },
                        isLoading = false,
                        isError = false
                    )
                }
            },
            onError = { error ->
                updateState {
                    copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = error.message
                    )
                }
            }
        )

    }

    private fun applyLocalFilters() {
        val allTransactions = currentState.allTransactions
        var filteredTransactions = when (currentState.typeFilter) {
            TransactionUiState.TransactionTypeFilter.ALL -> allTransactions
            TransactionUiState.TransactionTypeFilter.INCOMES -> allTransactions.filter { it.isExpense.not() }
            TransactionUiState.TransactionTypeFilter.EXPENSES -> allTransactions.filter { it.isExpense }
        }

        if (currentState.selectedCategoryIds.isNotEmpty()) {
            filteredTransactions = filteredTransactions.filter { transaction ->
                transaction.categoryId in currentState.selectedCategoryIds
            }
        }
        updateState { copy(transactions = filteredTransactions) }
    }
}