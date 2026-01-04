package com.moneyplusplus.domain.repository
import com.moneyplusplus.domain.entity.Account

interface AccountRepository {
    suspend fun saveAccount(account: Account)
    suspend fun getAccount(userId: String): Account
}