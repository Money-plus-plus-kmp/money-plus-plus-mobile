package com.moneyplusplus.data.di

import com.moneyplusplus.data.repository.StatisticsRepositoryImpl
import com.moneyplusplus.domain.repository.StatisticsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    includes(platformModule())
    factoryOf(::StatisticsRepositoryImpl) { bind<StatisticsRepository>() }
}
