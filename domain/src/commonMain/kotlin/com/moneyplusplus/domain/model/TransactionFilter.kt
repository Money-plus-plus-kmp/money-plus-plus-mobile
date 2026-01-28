package com.moneyplusplus.domain.model

import com.moneyplusplus.domain.entity.TransactionType
import kotlinx.datetime.LocalDate
import kotlin.uuid.Uuid

data class TransactionFilter(
    val type: TransactionType? = null,
    val date: LocalDate,
    val categoriesIds: List<Uuid>
)
