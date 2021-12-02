package com.underdog_tech.pinwheel_android.model

// Used as the parameter type of the event payload callback.
// All valid callback data types implement this.
interface PinwheelEventPayload {}

enum class PinwheelEventType {
    OPEN,
    SELECT_EMPLOYER,
    SELECT_PLATFORM,
    INPUT_AMOUNT,
    INPUT_REQUIRED,
    EXIT,
    SUCCESS,
    LOGIN,
    LOGIN_ATTEMPT,
    ERROR,
    INCORRECT_PLATFORM_GIVEN
}

const val EVENT_MESSAGE = "PINWHEEL_EVENT"

data class PinwheelAmount(
    val value: Float,
    val unit: String,
): PinwheelEventPayload

data class PinwheelParams(
    val amount: PinwheelAmount? = null,
)

data class PinwheelResult(
    val accountId: String,
    val platformId: String,
    val job: String,
    val params: PinwheelParams?,
): PinwheelEventPayload

data class PinwheelError(
    val type: String,
    val code: String,
    val message: String,
    val pendingRetry: Boolean,
): PinwheelEventPayload

data class PinwheelSelectedEmployerPayload(
    val selectedEmployerId: String,
    val selectedEmployerName: String,
): PinwheelEventPayload

data class PinwheelSelectedPlatformPayload(
    val selectedPlatformId: String,
    val selectedPlatformName: String,
): PinwheelEventPayload

data class PinwheelLoginPayload(
    val accountId: String,
    val platformId: String,
): PinwheelEventPayload

data class PinwheelLoginAttemptPayload(
    val platformId: String,
): PinwheelEventPayload


