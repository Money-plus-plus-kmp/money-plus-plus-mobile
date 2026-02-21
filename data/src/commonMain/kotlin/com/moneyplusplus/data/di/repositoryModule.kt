package com.moneyplusplus.data.di

import com.moneyplusplus.data.datasource.remote.auth.AuthDataSource
import com.moneyplusplus.data.datasource.remote.client.createHttpClient
import com.moneyplusplus.data.repository.AuthRepositoryImpl
import com.moneyplusplus.data.repository.StatisticsRepositoryImpl
import com.moneyplusplus.domain.repository.AuthRepository
import com.moneyplusplus.domain.repository.StatisticsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::createHttpClient)
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    singleOf(::StatisticsRepositoryImpl) bind StatisticsRepository::class
    singleOf(::AuthDataSource)
}