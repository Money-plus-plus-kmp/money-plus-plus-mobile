package com.moneyplusplus.domain.entity

import kotlinx.datetime.LocalDate

data class Income(
    val amount: Int,
    val date: LocalDate,
    val note: String
)
