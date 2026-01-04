package com.moneyplusplus.domain.repository
import com.moneyplusplus.domain.entity.Currency

interface CurrencyRepository {
    suspend fun getSupportedCurrencies(): List<Currency>
}
