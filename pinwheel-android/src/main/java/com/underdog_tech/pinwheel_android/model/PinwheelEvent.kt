package com.underdog_tech.pinwheel_android.model

data class PinwheelActionEvent(
    val type: String,
    val name: String,
    val pageName: String,
    val actionName: String,
)

data class PinwheelResult(
    val tokenId: String
)

data class PinwheelError(
    val errorCode: String,
    val errorMessage: String,
)

data class PinwheelSuccessEvent(
    val type: String,
    val result: PinwheelResult,
)

data class PinwheelExitEvent(
    val type: String,
    val result: PinwheelResult,
    val err: PinwheelError?
)


