package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import com.moneyplusplus.presentation.base.UiEffect
import com.moneyplusplus.presentation.base.UiIntent
import com.moneyplusplus.presentation.base.UiState

data class AccountSetupState(
    val loading: Boolean = false,
    val isCurrencyBottomSheetVisible: Boolean = false,
    val isSalaryDayPickerVisible: Boolean = false,
    val salaryDay: Int? = null,
    val salaryDayText: String = "",
    val salary: String = "",
    val selectedCurrency: CurrencyUiModel? = null
) : UiState

data class CurrencyUiModel(
    val name: String,
    val country: String,
    val code: String
)

sealed interface AccountSetupIntent : UiIntent {
    data object ClickCurrencyArrow : AccountSetupIntent
    data object DismissCurrencyBottomSheet : AccountSetupIntent
    data object SalaryDayClicked : AccountSetupIntent
    data class SalaryDaySelected(val day: Int) : AccountSetupIntent
    data class SalaryChanged(val newSalary: String) : AccountSetupIntent

}

sealed interface AccountSetupEffect : UiEffect {}