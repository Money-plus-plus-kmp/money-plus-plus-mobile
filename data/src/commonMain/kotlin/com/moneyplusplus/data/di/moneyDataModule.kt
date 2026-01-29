package com.moneyplusplus.data.di

import org.koin.dsl.module

val moneyDataModule = module {
    includes(repositoryModule, platformModule())
}
