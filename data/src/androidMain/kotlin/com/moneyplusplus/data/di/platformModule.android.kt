package com.moneyplusplus.data.di

import com.moneyplusplus.data.auth.GoogleAuthManager
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    singleOf(::GoogleAuthManager)
}
