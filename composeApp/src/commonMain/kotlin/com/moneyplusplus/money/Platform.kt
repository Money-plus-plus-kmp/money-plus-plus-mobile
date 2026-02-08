package com.moneyplusplus.money

interface Platform {
    val name: String
    val appVersion: String
}

expect fun getPlatform(): Platform
