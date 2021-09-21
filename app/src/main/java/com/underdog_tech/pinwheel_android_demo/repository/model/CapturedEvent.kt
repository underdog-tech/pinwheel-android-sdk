package com.underdog_tech.pinwheel_android_demo.repository.model

import java.io.Serializable

data class CapturedEvent(val eventName: String, var payload: String?) : Serializable