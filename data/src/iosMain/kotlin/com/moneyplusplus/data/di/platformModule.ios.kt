package com.moneyplusplus.data.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

internal actual fun platformModule() = module {
    single<HttpClientEngine> { Darwin.create() }
}
