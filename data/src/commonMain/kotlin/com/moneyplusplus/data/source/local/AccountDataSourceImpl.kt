package com.moneyplusplus.data.source.local

import com.moneyplusplus.domain.entity.Account

class AccountDataSourceImpl : AccountDataSource {
    // TODO: Replace with a persistent local source (SQLDelight/Datastore) when account storage is implemented.
    private val accounts = mutableMapOf<String, Account>()

    override suspend fun saveAccount(account: Account) {
        accounts[account.userId.toString()] = account
    }

    override suspend fun getAccount(userId: String): Account? {
        return accounts[userId]
    }
}
