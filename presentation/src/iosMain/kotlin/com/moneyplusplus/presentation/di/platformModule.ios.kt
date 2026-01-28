package com.moneyplusplus.presentation.di

import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun platformModule(): Module = module {
    // iOS-specific dependencies can be declared here
}
