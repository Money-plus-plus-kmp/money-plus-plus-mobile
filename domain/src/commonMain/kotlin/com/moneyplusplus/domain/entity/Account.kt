package com.moneyplusplus.domain.entity

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
@OptIn(ExperimentalUuidApi::class)
data class Account(
    val userId: Uuid,
    val currency: Currency,
    val salary: Salary,
    val currentBalance: Double,
    val categories: List<Category>
)
