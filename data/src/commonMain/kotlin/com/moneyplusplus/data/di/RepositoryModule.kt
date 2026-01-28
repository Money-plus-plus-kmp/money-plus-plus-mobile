package com.moneyplusplus.data.di

import com.moneyplusplus.data.repository.AuthRepositoryImpl
import com.moneyplusplus.data.repository.FakeTransactionRepository
import com.moneyplusplus.data.repository.fakedata.FakeCategoryRepository
import com.moneyplusplus.data.util.network.createHttpClient
import com.moneyplusplus.domain.repository.CategoryRepository
import com.moneyplusplus.domain.repository.TransactionRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


internal val repositoryModule = module {
    singleOf(::createHttpClient)
    singleOf(::AuthRepositoryImpl)
    singleOf(::FakeTransactionRepository) { bind<TransactionRepository>() }
    singleOf(::FakeCategoryRepository) { bind<CategoryRepository>() }
}