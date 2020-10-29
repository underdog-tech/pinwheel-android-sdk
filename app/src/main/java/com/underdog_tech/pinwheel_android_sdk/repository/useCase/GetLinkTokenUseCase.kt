package com.underdog_tech.pinwheel_android_sdk.repository.useCase

import com.underdog_tech.pinwheel_android_sdk.repository.model.GetLinkTokenRequest
import com.underdog_tech.pinwheel_android_sdk.repository.model.GetLinkTokenResponse
import com.underdog_tech.pinwheel_android_sdk.repository.network.ApiAdapter
import timber.log.Timber
import java.lang.Exception

object GetLinkTokenUseCase {

    suspend fun getToken(body: GetLinkTokenRequest): GetLinkTokenResponse? {
        try {
            val response = ApiAdapter.apiClient.getLinkToken(body)
            return response.body()
        } catch (e: Exception) {
            Timber.e(e)
        }
        return null
    }
}