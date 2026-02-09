package com.moneyplusplus.data.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

internal actual fun platformModule() = module {
    single<HttpClientEngine> { Android.create() }}
