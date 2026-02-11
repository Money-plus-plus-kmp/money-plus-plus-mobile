package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import com.moneyplusplus.presentation.base.BaseViewModel

class AccountSetupViewModel() :
    BaseViewModel<AccountSetupState, AccountSetupIntent, AccountSetupEffect>(
        initialState = AccountSetupState()
    ) {
    init {
        x()
    }
    override fun handleIntent(intent: AccountSetupIntent) {
        when (intent) {
            is AccountSetupIntent.SalaryChanged -> onSalaryChanged(intent.newSalary)
            is AccountSetupIntent.SalaryDaySelected -> onSalaryDaySelected(intent.day)
            is AccountSetupIntent.SelectCurrencyItem -> onCurrencyItemSelected(intent.currency)
            AccountSetupIntent.ClickCurrencyArrow -> onCurrencyArrowClicked()
            AccountSetupIntent.DismissCurrencyBottomSheet -> dismissCurrencyBottomSheet()
            AccountSetupIntent.SalaryDayClicked -> onSalaryDayClicked()
        }
    }

    private fun onCurrencyItemSelected(currency: CurrencyUiModel) {
        println(currentState.selectedCurrency)
        updateState { copy(selectedCurrency = currency) }
        println(currentState.selectedCurrency)
    }

    private fun onSalaryChanged(newSalary: String) {
        updateState { copy(salary = newSalary) }
    }

    private fun onCurrencyArrowClicked() {
        updateState { copy(isCurrencyBottomSheetVisible = true) }
    }

    private fun dismissCurrencyBottomSheet() {
        updateState { copy(isCurrencyBottomSheetVisible = false) }
    }

    private fun onSalaryDayClicked() {
        updateState { copy(isSalaryDayPickerVisible = true) }
    }

    private fun x() {
        updateState {
            copy(
                currencies = listOf(
                    CurrencyUiModel("Iraqi Dinar", "Iraq", "IQD"),
                    CurrencyUiModel("Egyptian Pound", "Egypt", "EGP"),
                    CurrencyUiModel("Syrian Pound", "Syria", "SYP"),
                    CurrencyUiModel("Jordanian Dinar", "Jordan", "JOD"),
                    CurrencyUiModel("Kuwaiti Dinar", "Kuwait", "KWD"),
                    CurrencyUiModel("Bahraini Dinar", "Bahrain", "BHD"),
                    CurrencyUiModel("Omani Rial", "Oman", "OMR"),
                    CurrencyUiModel("Qatari Rial", "Qatar", "QAR"),
                    CurrencyUiModel("Saudi Riyal", "Saudi Arabia", "SAR"),
                    CurrencyUiModel("Iraqi Dinar", "Iraq", "IQD"),
                    CurrencyUiModel("Egyptian Pound", "Egypt", "EGP"),
                    CurrencyUiModel("Syrian Pound", "Syria", "SYP"),
                    CurrencyUiModel("Jordanian Dinar", "Jordan", "JOD"),
                    CurrencyUiModel("Kuwaiti Dinar", "Kuwait", "KWD"),
                    CurrencyUiModel("Bahraini Dinar", "Bahrain", "BHD"),
                    CurrencyUiModel("Omani Rial", "Oman", "OMR"),
                    CurrencyUiModel("Qatari Rial", "Qatar", "QAR"),
                    CurrencyUiModel("Saudi Riyal", "Saudi Arabia", "SAR")
                )
            )
        }
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