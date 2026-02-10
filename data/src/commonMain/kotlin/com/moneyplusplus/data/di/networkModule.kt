package com.moneyplusplus.data.di

import com.moneyplusplus.data.util.network.createHttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val networkModule = module {
    single(named("baseUrl")) { getBaseUrl() }

    single { createHttpClientEngine() }

    single {
        createHttpClient(
            engine = get(),
            baseUrl = get(named("baseUrl"))
        )
    }

}

expect fun createHttpClientEngine(): HttpClientEngine

expect fun getBaseUrl(): String