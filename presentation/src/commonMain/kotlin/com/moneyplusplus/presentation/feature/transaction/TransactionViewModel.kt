package com.moneyplusplus.presentation.feature.transaction

import androidx.lifecycle.viewModelScope
import com.moneyplusplus.domain.model.TransactionFilter
import com.moneyplusplus.domain.usecase.transaction.GetTransactionsUseCase
import com.moneyplusplus.presentation.base.BaseViewModel
import com.moneyplusplus.presentation.model.toUiModel
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock


class TransactionViewModel(private val getTransactionsUseCase: GetTransactionsUseCase) :
    BaseViewModel<
            TransactionUiState,
            TransactionIntent,
            TransactionEffect>(
        TransactionUiState()
    ) {
    init {
        initDateThenLoadTransactions()
    }

    override fun handleIntent(intent: TransactionIntent) {
        when (intent) {
            is TransactionIntent.OnFilterClick -> {
                updateState { copy(showFilterBottomSheet = true) }
            }

            is TransactionIntent.OnFilterSheetDismissed -> {
                updateState { copy(showFilterBottomSheet = false) }
            }

            is TransactionIntent.OnTransactionTypeClick -> {
                if (currentState.typeFilter == intent.type) return
                updateState { copy(typeFilter = intent.type) }
                loadTransactions()
            }

            is TransactionIntent.OnDateClick -> {
                sendEffect(TransactionEffect.ShowDatePicker(currentState.date))
            }

            is TransactionIntent.OnDateSelected -> {
                updateState { copy(date = intent.date.toString()) }
                loadTransactions()
            }
        }
    }

    private fun initDateThenLoadTransactions() {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        updateState { copy(date = today.toString()) }
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {

            val filter = TransactionFilter(
                date = if (currentState.date.isEmpty()) {
                    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                } else {
                    LocalDate.parse(currentState.date)
                },
                type = currentState.typeFilter.toDomainTransactionType(),
                categories = emptyList()
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
                        errorMessage = it.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}