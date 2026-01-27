package com.moneyplusplus.domain.model

import com.moneyplusplus.domain.entity.Category
import com.moneyplusplus.domain.entity.TransactionType
import kotlinx.datetime.LocalDate

data class TransactionFilter(
    val type: TransactionType? = null,
    val date: LocalDate,
    val categories: List<Category>
)
