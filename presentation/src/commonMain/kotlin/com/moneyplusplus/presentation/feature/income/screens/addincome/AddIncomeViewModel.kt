package com.moneyplusplus.presentation.feature.income.screens.addincome

import com.moneyplusplus.domain.usecase.validation.AddIncomeUseCase
import com.moneyplusplus.presentation.base.BaseViewModel
import kotlinx.datetime.LocalDate

class AddIncomeViewModel(
    private val addIncomeUseCase: AddIncomeUseCase
) : BaseViewModel<AddIncomeState, AddIncomeIntent, AddIncomeEffect>(AddIncomeState()) {
    override fun handleIntent(intent: AddIncomeIntent) {
        when (intent) {
            AddIncomeIntent.AddIncome -> addIncome()
            is AddIncomeIntent.SetAmount -> setAmount(intent.amount)
            is AddIncomeIntent.SetDate -> setDate(intent.date)
            is AddIncomeIntent.SetNote -> setNote(intent.note)
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
            copy(amount = amount)
        }
    }

    private fun setDate(date: LocalDate) {
        updateState {
            copy(date = date)
        }
    }

    private fun setNote(note: String) {
        updateState {
            copy(note = note)
        }
    }

}