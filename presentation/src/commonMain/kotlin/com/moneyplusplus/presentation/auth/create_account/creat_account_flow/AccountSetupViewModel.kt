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
            AccountSetupIntent.ClickCurrencyArrow -> onCurrencyArrowClicked()
            AccountSetupIntent.DismissCurrencyBottomSheet -> dismissCurrencyBottomSheet()
            AccountSetupIntent.SalaryDayClicked -> onSalaryDayClicked()

        }
    }

    private fun onSalaryChanged(newSalary: String) {
        updateState { copy(salary = newSalary) }
    }

    private fun onCurrencyArrowClicked() {
        println("Currency Clicked ${currentState.isCurrencyBottomSheetVisible}")
        updateState { copy(isCurrencyBottomSheetVisible = true) }
        println("Currency Clicked ${currentState.isCurrencyBottomSheetVisible}")
    }

    private fun dismissCurrencyBottomSheet() {
        updateState { copy(isCurrencyBottomSheetVisible = false) }
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