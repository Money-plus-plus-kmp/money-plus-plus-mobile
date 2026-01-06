package com.moneyplusplus.money.di

import com.moneyplusplus.presentation.auth.create_account.CreateAccountViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule)
    }
}

fun doInitKoin() {
    initKoin { }
}

val appModule = module {
    singleOf(::CreateAccountViewModel)
}
