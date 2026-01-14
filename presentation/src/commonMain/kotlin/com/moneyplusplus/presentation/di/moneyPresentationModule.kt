package com.moneyplusplus.presentation.di

import org.koin.dsl.module

val moneyPresentationModule = module {
    includes(moneyViewModelModule)
}
