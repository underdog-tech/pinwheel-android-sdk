package com.underdog_tech.pinwheel_android_sdk.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.underdog_tech.pinwheel_android_sdk.repository.model.GetLinkTokenRequest
import com.underdog_tech.pinwheel_android_sdk.repository.useCase.GetLinkTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun generateLinkToken(onTokenGenerated: (String) -> Unit) {
        // todo provide body from outside
        val body = GetLinkTokenRequest(
            job = "direct_deposit_switch",
            orgName = "test",
            accountNumber = "2305215639",
            routingNumber = "775041380",
            accountType = "checking",
            accountName = null,
            amount = null,
            employerId = null
        )
        viewModelScope.launch(Dispatchers.IO) {
            val getTokenResponse = GetLinkTokenUseCase.getToken(body)
            getTokenResponse?.let { onTokenGenerated(it.data.token) }
        }
    }
}