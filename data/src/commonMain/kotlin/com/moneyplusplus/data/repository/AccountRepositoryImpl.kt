package com.moneyplusplus.data.repository

import com.moneyplusplus.domain.entity.Account
import com.moneyplusplus.domain.entity.Currency
import com.moneyplusplus.domain.entity.Salary
import com.moneyplusplus.domain.repository.AccountRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import com.moneyplusplus.data.source.local.AccountDataSource


@OptIn(ExperimentalUuidApi::class)
class AccountRepositoryImpl(
    private val dataSource: AccountDataSource
) : AccountRepository {
    override suspend fun saveAccount(account: Account) {
        dataSource.saveAccount(account)
    }

    override suspend fun getAccount(userId: String): Account {
        return dataSource.getAccount(userId) ?: dataSource.getAccount("default_user")!!
    }
}
