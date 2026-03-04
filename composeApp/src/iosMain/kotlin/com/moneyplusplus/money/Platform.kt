package com.moneyplusplus.money

import platform.Foundation.NSBundle
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val appVersion: String
        get() = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String ?: ""
}

actual fun getPlatform(): Platform = IOSPlatform()
