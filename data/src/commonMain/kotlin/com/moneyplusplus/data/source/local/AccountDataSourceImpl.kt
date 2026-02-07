package com.moneyplusplus.data.source.local

import com.moneyplusplus.domain.entity.Account
import com.moneyplusplus.domain.entity.Category
import com.moneyplusplus.domain.entity.Currency
import com.moneyplusplus.domain.entity.Salary
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class AccountDataSourceImpl : AccountDataSource {
    private val accounts = mutableMapOf<String, Account>()
    
    init {
        val defaultUserId = Uuid.random()
        val defaultAccount = Account(
            userId = defaultUserId,
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
        accounts["default_user"] = defaultAccount
    }
    
    override suspend fun saveAccount(account: Account) {
        accounts[account.userId.toString()] = account
    }
    
    override suspend fun getAccount(userId: String): Account? {
        return accounts[userId]
    }
}
