package com.moneyplusplus.data.di
import com.moneyplusplus.data.BuildConfig

actual fun getBaseUrl(): String = BuildConfig.BASE_URL // causes crash

//actual fun getBaseUrl(): String = "https://money-plus-dev.vercel.app" // safe (same url in the local properties