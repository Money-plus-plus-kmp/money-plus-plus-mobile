package com.moneyplusplus.data.di

import com.moneyplusplus.data.auth.AuthRepositoryImpl
import com.moneyplusplus.data.repository.IncomeRepositoryImpl
import com.moneyplusplus.domain.repository.AuthRepository
import com.moneyplusplus.domain.repository.IncomeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class

    single<IncomeRepository>{ IncomeRepositoryImpl(get()) }
}
