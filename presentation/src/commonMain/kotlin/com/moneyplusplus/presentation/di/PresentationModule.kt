package com.moneyplusplus.presentation.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule, platformModule())
}