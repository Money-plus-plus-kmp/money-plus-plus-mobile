package com.moneyplusplus.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddTransactionRequest(
    val type: TransactionType,
    val amount: Int,
    val category: String? = null,
    val date: String,
    val note: String = ""
)


@Serializable
enum class TransactionType {
    @SerialName("income")
    INCOME,

    @SerialName("expense")
    EXPENSE
}