package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import com.moneyplusplus.presentation.base.UiEffect
import com.moneyplusplus.presentation.base.UiIntent
import com.moneyplusplus.presentation.base.UiState

data class AccountSetupState(
    val loading: Boolean = false,
    val currentStep: AccountSetupStep = AccountSetupStep.STEP_ONE,
    val isCurrencyBottomSheetVisible: Boolean = false,
    val isSalaryDayPickerVisible: Boolean = false,
    val salaryDay: Int? = null,
    val salaryDayText: String = "",
    val salary: String = "",
    val selectedCurrency: CurrencyUiModel? = null,
    val currentBalance: String = "",
    val currencies: List<CurrencyUiModel> = emptyList()
) : UiState {
    val isFormValidForCurrentStep: Boolean
        get() = when (currentStep) {
            AccountSetupStep.STEP_ONE ->
                selectedCurrency != null &&
                        salary.isNotBlank() &&
                        salaryDay != null

            AccountSetupStep.STEP_TWO -> currentBalance.isNotBlank()
            AccountSetupStep.STEP_THREE -> currentBalance.isNotBlank()
        }
}

data class CurrencyUiModel(
    val name: String,
    val country: String,
    val code: String
)

val CurrencyUiModel.displayName: String
    get() = "$name-$code"

enum class AccountSetupStep {
    STEP_ONE,
    STEP_TWO,
    STEP_THREE,
}

sealed interface AccountSetupIntent : UiIntent {
    data object ClickCurrencyArrow : AccountSetupIntent
    data object DismissCurrencyBottomSheet : AccountSetupIntent
    data object SalaryDayClicked : AccountSetupIntent
    data class SelectCurrencyItem(val currency: CurrencyUiModel) : AccountSetupIntent
    data class SalaryDaySelected(val day: Int) : AccountSetupIntent
    data class SalaryChanged(val newSalary: String) : AccountSetupIntent
    data class CurrentBalanceChanged(val newBalance: String) : AccountSetupIntent
    data object NextClicked : AccountSetupIntent
    data object FinishClicked : AccountSetupIntent

}

sealed interface AccountSetupEffect : UiEffect {}