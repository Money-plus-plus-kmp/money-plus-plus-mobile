package com.moneyplusplus.data.mapper

import com.moneyplusplus.data.dto.TransactionResponse
import com.moneyplusplus.domain.entity.Currency
import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.entity.TransactionType
import kotlinx.datetime.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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
    category = categoryResponse.toDomain()
)