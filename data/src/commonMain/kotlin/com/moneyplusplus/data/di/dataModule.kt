package com.moneyplusplus.data.di

import org.koin.dsl.module


val dataModule = module {
    includes(repositoryModule)
}
