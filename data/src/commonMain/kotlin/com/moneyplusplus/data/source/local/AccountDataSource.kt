package com.moneyplusplus.data.source.local

import com.moneyplusplus.domain.entity.Account

interface AccountDataSource {
    suspend fun saveAccount(account: Account)
    suspend fun getAccount(userId: String): Account?
}
