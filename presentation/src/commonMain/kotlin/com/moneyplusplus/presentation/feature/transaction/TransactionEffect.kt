package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.presentation.base.UiEffect

sealed class TransactionEffect: UiEffect {
    data class ShowDatePicker(val date: String): TransactionEffect()
}

