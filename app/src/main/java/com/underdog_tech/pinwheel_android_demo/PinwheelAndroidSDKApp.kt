package com.underdog_tech.pinwheel_android_demo

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class PinwheelAndroidSDKApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}