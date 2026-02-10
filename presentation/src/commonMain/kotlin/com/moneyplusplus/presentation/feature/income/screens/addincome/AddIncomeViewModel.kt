package com.moneyplusplus.presentation.feature.income.screens.addincome

import com.moneyplusplus.domain.usecase.AddIncome
import com.moneyplusplus.presentation.base.BaseViewModel
import kotlinx.datetime.LocalDate

class AddIncomeViewModel(
    private val addIncome: AddIncome
) : BaseViewModel<AddIncomeState, AddIncomeIntent, AddIncomeEffect>(AddIncomeState()) {

    init {
        // Temp until get User Data
        // Then get the user's currency

        updateState {
            copy(
                currencyCode = "EGP"
            )
        }

    }
    override fun handleIntent(intent: AddIncomeIntent) {
        when (intent) {
            AddIncomeIntent.OnAddIncomeClick -> addIncome()
            is AddIncomeIntent.SetAmount -> setAmount(intent.amount)
            is AddIncomeIntent.SetDate -> setDate(intent.date)
            is AddIncomeIntent.SetNote -> setNote(intent.note)
            is AddIncomeIntent.OnDateClick -> showDatePicker()
            is AddIncomeIntent.OnBackClick -> navigateBack()
            is AddIncomeIntent.OnDatePickerDismiss -> dismissDatePicker()
        }
    }

    private fun addIncome() {
        tryExecute(
            onSuccess = { sendEffect(AddIncomeEffect.ShowSuccess) },
            onError = {
                println("Trace + ${it.printStackTrace()}")
                sendEffect(AddIncomeEffect.ShowError)
                      },
            block = { addIncome(currentState.toIncome() ?: return@tryExecute) }
        )
    }

    private fun setAmount(amount: Int?) {
        updateState {
            copy(
                amount = amount,
                isAddEnabled = isAddEnabled(amount)
            )
        }
    }

    private fun setDate(date: LocalDate) {
        updateState {
            copy(
                date = date,
                isDatePickerVisible = false
            )
        }
    }

    private fun setNote(note: String) {
        updateState {
            copy(note = note)
        }
    }

    private fun showDatePicker() {
        updateState { copy(isDatePickerVisible = true) }
    }

    private fun dismissDatePicker() {
        updateState {
            copy(isDatePickerVisible = false)
        }
    }

    private fun navigateBack() {
        sendEffect(AddIncomeEffect.NavigateBack)
    }

    private fun isAddEnabled(amount: Int?) = amount != null && amount > 0

}