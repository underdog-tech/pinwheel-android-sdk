package com.underdog_tech.pinwheel_android

import com.underdog_tech.pinwheel_android.model.*

interface PinwheelEventListener {
    fun onLogin(result: PinwheelLoginPayload) {}

    fun onSuccess(result: PinwheelResult) {}

    fun onError(error: PinwheelError) {}

    fun onExit(error: PinwheelError?) {}

    fun onEvent(eventName: PinwheelEventType, payload: PinwheelEventPayload?) {}
}