package com.moneyplusplus.presentation.feature.income.screens.addincome

import com.moneyplusplus.domain.entity.Currency
import com.moneyplusplus.domain.entity.Income
import com.moneyplusplus.presentation.base.UiState
import com.moneyplusplus.presentation.utils.currentDate
import kotlinx.datetime.LocalDate

data class AddIncomeState(
    val amount: Int? = null,
    val currencyCode: String = "IQD", // temp until get default currency from settings
    val date: LocalDate = LocalDate.currentDate(),
    val note: String = "",
    val isAddEnabled: Boolean = false,
    val isDatePickerVisible: Boolean = false
): UiState

fun AddIncomeState.toIncome(): Income? {
    if (amount == null) return null
    val currency = Currency( // temp until fetching currencies
        code = currencyCode,
        name = "Iraqi Dinar",
        country = "Iraq"
    )

    return Income(
        amount = amount,
        currency = currency,
        date = date,
        note = note
    )
}