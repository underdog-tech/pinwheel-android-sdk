package com.underdog_tech.pinwheel_android

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.util.*

class UUIDManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE)

    companion object {
        const val PREFS_KEY = "PinwheelPrefs"
    }

    val uuid: String?
        get() {
        return sharedPreferences.getString("uuid", UUID.randomUUID().toString())
    }

}