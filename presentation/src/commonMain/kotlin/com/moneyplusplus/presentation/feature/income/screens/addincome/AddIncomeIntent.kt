package com.moneyplusplus.presentation.feature.income.screens.addincome

import com.moneyplusplus.presentation.base.UiIntent
import kotlinx.datetime.LocalDate

sealed interface AddIncomeIntent: UiIntent {
    data class SetAmount(val amount: Int): AddIncomeIntent
    data class SetDate(val date: LocalDate): AddIncomeIntent
    data class SetNote(val note: String): AddIncomeIntent
    data object AddIncome: AddIncomeIntent
}