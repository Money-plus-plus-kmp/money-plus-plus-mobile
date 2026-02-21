package com.moneyplusplus.presentation.feature.income.screens.addincome

import com.moneyplusplus.presentation.base.UiEffect
import com.moneyplusplus.presentation.base.UiIntent
import com.moneyplusplus.domain.entity.Income
import com.moneyplusplus.presentation.base.UiState
import com.moneyplusplus.presentation.utils.currentDate
import kotlinx.datetime.LocalDate


data class AddIncomeState(
    val amount: Int? = null,
    val currencyCode: String? = null, // temp until get default currency from settings
    val date: LocalDate = LocalDate.currentDate(),
    val note: String = "",
    val isAddEnabled: Boolean = false,
    val isDatePickerVisible: Boolean = false
): UiState

fun AddIncomeState.toIncome(): Income? {
    if (amount == null) return null

    return Income(
        amount = amount,
        date = date,
        note = note
    )
}

sealed interface AddIncomeEffect: UiEffect {
    data object ShowSuccess: AddIncomeEffect
    data object ShowError: AddIncomeEffect
    data object NavigateBack: AddIncomeEffect
}

sealed interface AddIncomeIntent: UiIntent {
    data class SetAmount(val amount: Int?): AddIncomeIntent
    data class SetNote(val note: String): AddIncomeIntent
    data class SetDate(val date: LocalDate): AddIncomeIntent

    data object OnDateClick: AddIncomeIntent
    data object OnAddIncomeClick: AddIncomeIntent
    data object OnBackClick: AddIncomeIntent

    data object OnDatePickerDismiss: AddIncomeIntent
}