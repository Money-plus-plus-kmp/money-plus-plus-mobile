package com.moneyplusplus.data.repository

import com.moneyplusplus.data.source.local.AccountDataSource
import com.moneyplusplus.domain.entity.Account
import com.moneyplusplus.domain.entity.Currency
import com.moneyplusplus.domain.entity.Salary
import com.moneyplusplus.domain.repository.AccountRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class AccountRepositoryImpl(
    private val dataSource: AccountDataSource
) : AccountRepository {
    override suspend fun saveAccount(account: Account) {
        dataSource.saveAccount(account)
    }

    override suspend fun getAccount(userId: String): Account {
        return dataSource.getAccount(userId) ?: createDefaultAccount(userId).also {
            dataSource.saveAccount(it)
        }
    }

    private fun createDefaultAccount(userId: String): Account {
        val resolvedUserId = runCatching { Uuid.parse(userId) }
            .getOrElse { Uuid.random() }
        return Account(
            userId = resolvedUserId,
            currency = Currency(
                code = "USD",
                name = "US Dollar",
                country = "United States"
            ),
            salary = Salary(
                amount = 0.0,
                paymentDay = 1
            ),
            currentBalance = 0.0,
            categories = emptyList()
        )
    }
}
