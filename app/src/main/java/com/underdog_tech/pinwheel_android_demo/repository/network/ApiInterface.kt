package com.underdog_tech.pinwheel_android_demo.repository.network

import com.underdog_tech.pinwheel_android_demo.repository.model.GetLinkTokenRequest
import com.underdog_tech.pinwheel_android_demo.repository.model.GetLinkTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/v1/link_tokens")
    suspend fun getLinkToken(@Body body: GetLinkTokenRequest): Response<GetLinkTokenResponse>
}