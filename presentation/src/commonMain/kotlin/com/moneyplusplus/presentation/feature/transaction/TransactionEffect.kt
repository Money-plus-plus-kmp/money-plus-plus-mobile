package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.presentation.base.UiEffect

sealed interface TransactionEffect: UiEffect {
    data class ShowSnackbarError(val message: String) : TransactionEffect
    data object NavigateToAddTransaction : TransactionIntent
    data object NavigateToMakeExpense : TransactionIntent
}

