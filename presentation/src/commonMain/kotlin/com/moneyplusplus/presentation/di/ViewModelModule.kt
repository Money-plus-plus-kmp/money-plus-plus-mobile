package com.moneyplusplus.presentation.di

import com.moneyplusplus.presentation.feature.forgetPassword.ForgetPasswordViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ForgetPasswordViewModel)
}