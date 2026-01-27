package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.presentation.base.UiIntent
import kotlinx.datetime.LocalDate

sealed class TransactionIntent : UiIntent {
    object OnFilterClick : TransactionIntent()
    data object OnFilterSheetDismissed : TransactionIntent()
    object OnDateClick : TransactionIntent()
    data class OnDateSelected(val date: LocalDate) : TransactionIntent()
    data class OnTransactionTypeClick(val type: TransactionTypeFilter) : TransactionIntent()
}