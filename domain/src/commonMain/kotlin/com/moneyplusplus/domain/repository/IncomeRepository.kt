package com.moneyplusplus.domain.repository

import com.moneyplusplus.domain.entity.Income

interface IncomeRepository {
    suspend fun addIncome(income: Income)
}