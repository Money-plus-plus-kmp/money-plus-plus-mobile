package com.moneyplusplus.presentation.feature.income.screens.addincome

import com.moneyplusplus.presentation.base.UiIntent
import kotlinx.datetime.LocalDate

sealed interface AddIncomeIntent: UiIntent {
    data class SetAmount(val amount: Int?): AddIncomeIntent
    data class SetCurrency(val currencyCode: String): AddIncomeIntent
    data class SetNote(val note: String): AddIncomeIntent
    data class SetDate(val date: LocalDate): AddIncomeIntent

    data object OnDateClick: AddIncomeIntent
    data object OnAddIncomeClick: AddIncomeIntent
    data object OnBackClick: AddIncomeIntent

    data object OnDatePickerDismiss: AddIncomeIntent
}