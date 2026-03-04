package com.moneyplusplus.money

import android.app.Application
import com.moneyplusplus.money.di.initKoin
import org.koin.android.ext.koin.androidContext

class MoneyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MoneyApplication)
        }
    }
}
