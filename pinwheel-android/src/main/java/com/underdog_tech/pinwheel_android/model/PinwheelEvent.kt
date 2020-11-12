package com.underdog_tech.pinwheel_android.model

data class PinwheelActionEvent(
    val type: String,
    val name: String,
    val payload: PinwheelEventPayload,
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

data class PinwheelEventPayload(
    val job: String?,
    val mode: String?,
    val orgName: String?,
    val accountName: String?,
    val accountType: String?,
    val accountNumber: String?,
    val routingNumber: String?,
    val platformKey: String?,
    val employerId: String?,
    val amount: String?,
    val skipIntroScreen: Boolean?,
    val skipExitSurvey: Boolean?,
    val skipEmployerSearch: Boolean?,
    val apiKey: String?,
    val employerSearchText: String?,
    val reason: String?,
    val otherReasonText: String?,
    val foundFromSearch: Boolean?,
    val inputtedProvider: String?,
    val searchText: String?,
    val step: String?,
    val href: String?,
    val label: String?,
    val errorCode: String?,
    val errorMsg: String?,
    val pageName: String?,
    val actionName: String?,
    val platformId: String?,
    val selectedEmployerId: String?,
    val selectedEmployerName: String?,
    val modalSessionId: String?,
    val uniqueUserId: String?,
)


