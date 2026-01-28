package com.moneyplusplus.money.di

import com.moneyplusplus.presentation.di.moneyPresentationModule
import org.koin.core.context.startKoin
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
    includes(
        moneyPresentationModule,
    )
}
