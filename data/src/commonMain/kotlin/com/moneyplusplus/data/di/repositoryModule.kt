package com.moneyplusplus.data.di

import com.moneyplusplus.data.repository.AccountRepositoryImpl
import com.moneyplusplus.data.auth.AuthRepositoryImpl
import com.moneyplusplus.data.source.local.AccountDataSource
import com.moneyplusplus.data.source.local.LocalAccountDataSource
import com.moneyplusplus.domain.repository.AccountRepository
import com.moneyplusplus.domain.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    singleOf(::LocalAccountDataSource) bind AccountDataSource::class
    singleOf(::AccountRepositoryImpl) bind AccountRepository::class
}
