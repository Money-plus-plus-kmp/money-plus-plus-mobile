package com.moneyplusplus.money

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val appVersion: String = BuildConfig.VERSION_NAME
}

actual fun getPlatform(): Platform = AndroidPlatform()
