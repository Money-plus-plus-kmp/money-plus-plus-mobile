package com.moneyplusplus.data.di

import com.moneyplusplus.data.datasource.remote.client.createHttpClient
import com.moneyplusplus.data.repository.AuthRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::createHttpClient)
    singleOf(::AuthRepositoryImpl)
}