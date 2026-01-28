package com.moneyplusplus.presentation.di

import org.koin.core.module.dsl.viewModelOf
import com.moneyplusplus.presentation.auth.create_account.CreateAccountViewModel
import org.koin.dsl.module

internal val moneyViewModelModule = module {
    viewModelOf(::CreateAccountViewModel)
}
