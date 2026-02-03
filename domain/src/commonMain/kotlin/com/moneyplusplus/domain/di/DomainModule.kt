package com.moneyplusplus.domain.di

import com.moneyplusplus.domain.usecase.validation.AddIncomeUseCase
import com.moneyplusplus.domain.usecase.validation.EmailValidator
import com.moneyplusplus.domain.usecase.validation.NameValidator
import com.moneyplusplus.domain.usecase.validation.PasswordValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::EmailValidator)
    singleOf(::NameValidator)
    singleOf(::PasswordValidator)

    singleOf(::AddIncomeUseCase)
}