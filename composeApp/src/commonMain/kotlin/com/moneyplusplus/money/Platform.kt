package com.moneyplusplus.money

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform