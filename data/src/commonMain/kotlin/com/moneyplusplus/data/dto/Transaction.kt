package com.moneyplusplus.data.dto

import com.moneyplusplus.domain.entity.Currency
import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.entity.TransactionType
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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
    val categories: List<CategoryResponse>,
    @SerialName("currency")
    val currency: CurrencyDto,
    @SerialName("created_at")
    val createdAt: String,
)


@OptIn(ExperimentalUuidApi::class)
fun TransactionResponse.toDomain() = Transaction(
    id = Uuid.parse(id),
    type = TransactionType.valueOf(type.uppercase()),
    amount = amount,
    date = LocalDateTime.parse(createdAt),
    title = title,
    currency = Currency(
        code = currency.code,
        name = currency.name,
        country = currency.country
    ),
    categories = categories.map { it.toDomain() },
)