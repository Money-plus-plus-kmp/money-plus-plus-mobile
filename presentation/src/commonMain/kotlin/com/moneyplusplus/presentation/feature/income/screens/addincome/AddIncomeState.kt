package com.moneyplusplus.presentation.feature.income.screens.addincome

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