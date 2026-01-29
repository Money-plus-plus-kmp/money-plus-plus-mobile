package com.moneyplusplus.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("type")
    val type: String,
    @SerialName("amount")
    val amount: Double,
    @SerialName("categories")
    val categoryResponse: CategoryResponse,
    @SerialName("currency")
    val currency: CurrencyDto,
    @SerialName("created_at")
    val createdAt: String,
)