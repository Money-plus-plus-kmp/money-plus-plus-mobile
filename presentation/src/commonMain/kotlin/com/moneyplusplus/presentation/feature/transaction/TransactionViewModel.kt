package com.moneyplusplus.presentation.feature.transaction

import androidx.lifecycle.viewModelScope
import com.moneyplusplus.domain.model.TransactionFilter
import com.moneyplusplus.domain.repository.CategoryRepository
import com.moneyplusplus.domain.usecase.transaction.GetTransactionsUseCase
import com.moneyplusplus.presentation.base.BaseViewModel
import com.moneyplusplus.presentation.model.toUiModel
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.uuid.Uuid


class TransactionViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val categoryRepository: CategoryRepository,
) :
    BaseViewModel<
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
                loadTransactions()
            }

            is TransactionIntent.OnTransactionTypeClick -> {
                if (currentState.typeFilter == intent.type) return
                updateState { copy(typeFilter = intent.type) }
                loadTransactions()
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
        viewModelScope.launch {

            val filter = TransactionFilter(
                date = currentState.date ?: Clock.System.now()
                    .toLocalDateTime(TimeZone.currentSystemDefault()).date,
                type = currentState.typeFilter.toDomainTransactionType(),
                categoriesIds = currentState.selectedCategoryIds.map { Uuid.parse(it) }
            )

            updateState { copy(isLoading = true, isError = false) }

            val result = getTransactionsUseCase(filter)
            result.onSuccess { transactions ->
                updateState {
                    copy(
                        transactions = transactions.map { it.toUiModel() },
                        isLoading = false,
                        isError = false
                    )
                }

            }.onFailure {
                updateState {
                    copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = it.message
                    )
                }
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            val result = categoryRepository.getCategories()
            result.onSuccess { categories ->
                updateState {
                    copy(categories = categories.map { category ->
                        category.toUiModel()
                    })
                }
            }.onFailure {
                updateState { copy(isError = true, errorMessage = it.message) }
            }

        }

    }
}