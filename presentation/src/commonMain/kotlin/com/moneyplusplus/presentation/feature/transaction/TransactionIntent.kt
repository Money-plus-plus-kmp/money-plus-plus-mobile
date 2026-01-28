package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.presentation.base.UiIntent
import kotlinx.datetime.LocalDate

sealed class TransactionIntent : UiIntent {
    data object OnFilterClick : TransactionIntent()
    data class OnApplyFilterClick(val selectedCategoryIds: List<String>) : TransactionIntent()
    data object OnFilterSheetDismissed : TransactionIntent()
    data object OnDateClick : TransactionIntent()
    data class OnDateSelected(val date: LocalDate) : TransactionIntent()
    data class OnTransactionTypeClick(val type: TransactionTypeFilter) : TransactionIntent()
    data object OnAddTransactionClick : TransactionIntent()
    data object OnAddTransactionSheetDismissed : TransactionIntent()

}
