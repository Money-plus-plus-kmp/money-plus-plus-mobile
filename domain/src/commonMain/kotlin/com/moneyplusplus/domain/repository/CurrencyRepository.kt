package com.moneyplusplus.domain.repository
import com.moneyplusplus.domain.entity.Currency

interface CurrencyRepository {
    suspend fun getCurrencies(query: String): List<Currency>
}