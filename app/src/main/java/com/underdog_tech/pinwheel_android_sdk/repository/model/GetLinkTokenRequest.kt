package com.underdog_tech.pinwheel_android_sdk.repository.model

import com.google.gson.annotations.SerializedName

data class GetLinkTokenRequest(
    val job: String,
    @SerializedName("org_name") val orgName: String,
    @SerializedName("account_number") val accountNumber: String,
    @SerializedName("routing_number") val routingNumber: String,
    @SerializedName("account_type") val accountType: String,
    @SerializedName("account_name") val accountName: String?,
    val amount: Int?,
    @SerializedName("employer_id") val employerId: String?,
    @SerializedName("disable_direct_deposit_splitting") val disableDirectDepositSplitting: Boolean? = false,
    @SerializedName("skip_employer_search") val skipEmployerSearch: Boolean? = false,
    @SerializedName("skip_exit_survey") val skipExitSurvey: Boolean? = false
    )