package com.underdog_tech.pinwheel_android_demo.repository.model

import com.google.gson.annotations.SerializedName

data class GetLinkTokenResponse(
    val data: GetLinkTokenResponseData
)

data class GetLinkTokenResponseData(
    val mode: String,
    val token: String,
    val expires: Long,
    @SerializedName("token_id") val tokenId: String,
)