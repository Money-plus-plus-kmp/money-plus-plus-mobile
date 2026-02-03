package com.moneyplusplus.presentation.feature.income.screens.addincome

import com.moneyplusplus.domain.usecase.validation.AddIncomeUseCase
import com.moneyplusplus.presentation.base.BaseViewModel
import kotlinx.datetime.LocalDate

class AddIncomeViewModel(
    private val addIncomeUseCase: AddIncomeUseCase
) : BaseViewModel<AddIncomeState, AddIncomeIntent, AddIncomeEffect>(AddIncomeState()) {

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
            onError = { sendEffect(AddIncomeEffect.ShowError) },
            block = { addIncomeUseCase(currentState.toIncome()) }
        )
    }

    private fun setAmount(amount: Int) {
        updateState {
            copy(
                amount = amount,
                isAddEnabled = isAddEnabled(this)
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

    private fun isAddEnabled(state: AddIncomeState): Boolean {
        return state.amount > 0
    }

}