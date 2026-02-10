package com.moneyplusplus.presentation.di

import com.moneyplusplus.presentation.auth.create_account.CreateAccountViewModel
import com.moneyplusplus.presentation.feature.forgetPassword.ForgetPasswordViewModel
import com.moneyplusplus.presentation.login.LoginViewModel
import com.moneyplusplus.presentation.feature.home.HomeViewModel
import com.moneyplusplus.presentation.feature.income.screens.addincome.AddIncomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ForgetPasswordViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CreateAccountViewModel)
    viewModelOf(::AddIncomeViewModel)
}