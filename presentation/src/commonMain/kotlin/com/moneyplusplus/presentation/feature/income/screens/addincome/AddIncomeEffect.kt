package com.moneyplusplus.presentation.feature.income.screens.addincome

import com.moneyplusplus.presentation.base.UiEffect

sealed interface AddIncomeEffect: UiEffect {
    data object ShowSuccess: AddIncomeEffect
    data object ShowError: AddIncomeEffect
}