package com.moneyplusplus.domain.entity

import kotlinx.datetime.LocalDateTime
import kotlin.uuid.Uuid

data class Transaction (
    val id: Uuid,
    val title: String,
    val amount: Double,
    val currency: Currency,
    val date: LocalDateTime,
    val type: TransactionType,
    val categories: List<Category>,

    )

enum class TransactionType {
    INCOME,
    EXPENSE
}