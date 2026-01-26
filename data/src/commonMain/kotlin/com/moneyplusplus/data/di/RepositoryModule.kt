package com.moneyplusplus.data.di

import com.moneyplusplus.data.repository.AuthRepositoryImpl
import com.moneyplusplus.data.util.network.createHttpClient
import com.moneyplusplus.domain.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::createHttpClient)
    singleOf(::AuthRepositoryImpl)
}