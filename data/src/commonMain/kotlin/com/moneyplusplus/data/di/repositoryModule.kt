package com.moneyplusplus.data.di

import com.moneyplusplus.data.auth.AuthRepositoryImpl
import com.moneyplusplus.data.repository.FakeTransactionRepository
import com.moneyplusplus.data.repository.fakedata.FakeCategoryRepository
import com.moneyplusplus.domain.repository.AuthRepository
import com.moneyplusplus.domain.repository.CategoryRepository
import com.moneyplusplus.domain.repository.TransactionRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    singleOf(::FakeTransactionRepository) bind TransactionRepository::class
    singleOf(::FakeCategoryRepository) bind CategoryRepository::class
}