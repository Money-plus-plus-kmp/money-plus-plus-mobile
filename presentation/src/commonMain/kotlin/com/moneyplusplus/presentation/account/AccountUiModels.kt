package com.moneyplusplus.presentation.account

import com.moneyplusplus.domain.entity.Account
import com.moneyplusplus.domain.entity.User

data class UserUi(
    val name: String,
    val email: String
)

data class AccountUi(
    val currencyCode: String,
    val currencyName: String,
    val currencyCountry: String,
    val salaryAmount: Double,
    val paymentDay: Int,
    val currentBalance: Double,
    val categoriesCount: Int
)

fun User.toUi(): UserUi = UserUi(
    name = name,
    email = email
)

fun Account.toUi(): AccountUi = AccountUi(
    currencyCode = currency.code,
    currencyName = currency.name,
    currencyCountry = currency.country,
    salaryAmount = salary.amount,
    paymentDay = salary.paymentDay,
    currentBalance = currentBalance,
    categoriesCount = categories.size
)
