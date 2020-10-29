package com.underdog_tech.pinwheel_android

import com.underdog_tech.pinwheel_android.model.PinwheelActionEvent
import com.underdog_tech.pinwheel_android.model.PinwheelExitEvent
import com.underdog_tech.pinwheel_android.model.PinwheelSuccessEvent

interface PinwheelEventListener {

    fun onSuccess(successEvent: PinwheelSuccessEvent)

    fun onExit(exitEvent: PinwheelExitEvent)

    fun onEvent(actionEvent: PinwheelActionEvent)
}