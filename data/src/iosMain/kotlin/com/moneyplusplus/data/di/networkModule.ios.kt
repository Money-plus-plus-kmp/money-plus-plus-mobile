package com.moneyplusplus.data.di

import platform.Foundation.NSBundle

actual fun getBaseUrl(): String {
    return NSBundle.mainBundle.objectForInfoDictionaryKey("BASE_URL") as? String
        ?: error("BASE_URL not found in Info.plist")
}