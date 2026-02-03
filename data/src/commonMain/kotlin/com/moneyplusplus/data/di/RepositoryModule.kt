package com.moneyplusplus.data.di

import com.moneyplusplus.data.repository.AuthRepositoryImpl
import com.moneyplusplus.data.repository.IncomeRepositoryImpl
import com.moneyplusplus.data.util.network.createHttpClient
import com.moneyplusplus.domain.repository.AuthRepository
import com.moneyplusplus.domain.repository.IncomeRepository
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {
    single { createHttpClientEngine() }
    singleOf(::createHttpClient)
    singleOf(::AuthRepositoryImpl)

    single<IncomeRepository>{ IncomeRepositoryImpl(get()) }
}

expect fun createHttpClientEngine(): HttpClientEngine