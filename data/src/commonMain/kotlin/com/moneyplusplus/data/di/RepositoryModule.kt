package com.moneyplusplus.data.di

import com.moneyplusplus.data.auth.AuthRepositoryImpl
import com.moneyplusplus.domain.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}
