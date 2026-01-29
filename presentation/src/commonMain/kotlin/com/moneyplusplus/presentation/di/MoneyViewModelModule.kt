package com.moneyplusplus.presentation.di

import org.koin.core.module.dsl.viewModelOf
import com.moneyplusplus.presentation.auth.create_account.CreateAccountViewModel
import com.moneyplusplus.presentation.feature.transaction.TransactionViewModel
import org.koin.dsl.module

internal val moneyViewModelModule = module {
    viewModelOf(::CreateAccountViewModel)
    viewModelOf(::TransactionViewModel)
}
