package com.moneyplusplus.domain.entity

data class Account(
    val userId: String,
    val currency: Currency,
    val salary: Salary,
    val currentBalance: Double,
    val categories: List<Category>
)
