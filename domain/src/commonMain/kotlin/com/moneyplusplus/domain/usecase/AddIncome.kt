package com.moneyplusplus.domain.usecase

import com.moneyplusplus.domain.entity.Income
import com.moneyplusplus.domain.repository.IncomeRepository
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

class AddIncome(
    private val incomeRepository: IncomeRepository
) {
    suspend operator fun invoke(income: Income) {
        println("Trace + Use case $income")
        validateIncomeAmount(income.amount)
        validateIncomeDate(income.date)
        incomeRepository.addIncome(income)
    }

    private fun validateIncomeDate(date: LocalDate): Boolean {
        return date <= LocalDate.currentDate()
    }

    private fun validateIncomeAmount(amount: Int): Boolean {
        return amount > 0
    }

    private fun LocalDate.Companion.currentDate(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}