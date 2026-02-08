package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import com.moneyplusplus.presentation.base.BaseViewModel

class AccountSetupViewModel() :
    BaseViewModel<AccountSetupState, AccountSetupIntent, AccountSetupEffect>(
        initialState = AccountSetupState()
    ) {
    override fun handleIntent(intent: AccountSetupIntent) {
        when (intent) {
            is AccountSetupIntent.SalaryChanged -> onSalaryChanged(intent.newSalary)
            is AccountSetupIntent.SalaryDaySelected -> onSalaryDaySelected(intent.day)
            AccountSetupIntent.ToggleCurrencyArrow -> onCurrencyArrowToggled()
            AccountSetupIntent.SalaryDayClicked -> onSalaryDayClicked()

        }
    }

    private fun onSalaryChanged(newSalary: String) {
        updateState { copy(salary = newSalary) }
    }

    private fun onCurrencyArrowToggled() {
        updateState { copy(isCurrencyBottomSheetVisible = !isCurrencyBottomSheetVisible) }
    }

    private fun onSalaryDayClicked() {
        updateState { copy(isSalaryDayPickerVisible = true) }
    }

    private fun onSalaryDaySelected(day: Int) {
        updateState {
            copy(
                salaryDay = day,
                salaryDayText = day.toString(),
                isSalaryDayPickerVisible = false
            )
        }
    }
}