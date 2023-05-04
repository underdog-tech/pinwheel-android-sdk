package com.underdog_tech.pinwheel_android.model

// Used as the parameter type of the event payload callback.
// All valid callback data types implement this.
interface PinwheelEventPayload {}

enum class PinwheelEventType {
    OPEN,
    SELECT_EMPLOYER,
    SELECT_PLATFORM,
    INPUT_AMOUNT,
    INPUT_ALLOCATION,
    INPUT_REQUIRED,
    EXIT,
    SUCCESS,
    LOGIN,
    LOGIN_ATTEMPT,
    ERROR,
    INCORRECT_PLATFORM_GIVEN,
    CARD_SWITCH_BEGIN,
    DD_FORM_BEGIN,
    DD_FORM_CREATE,
    DD_FORM_DOWNLOAD,
    SCREEN_TRANSITION,
}

const val EVENT_MESSAGE = "PINWHEEL_EVENT"

data class PinwheelAmount(
    val value: Float,
    val unit: String,
): PinwheelEventPayload

data class PinwheelTarget(
    val accountType: String,
    val accountName: String?,
)

data class PinwheelAllocation(
    val type: String,
    val value: Float?,
    val target: PinwheelTarget?,
)

data class PinwheelInputAmountPayload(
    val action: String,
    val allocation: PinwheelAllocation?,
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

data class PinwheelDDFormCreatePayload(
    val url: String,
): PinwheelEventPayload

data class PinwheelScreenTransitionPayload(
    val screenName: String,
    val selectedEmployerId: String?,
    val selectedEmployerName: String?,
    val selectedPlatformId: String?,
    val selectedPlatformName: String?,
): PinwheelEventPayload
