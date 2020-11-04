package com.underdog_tech.pinwheel_android_demo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.underdog_tech.pinwheel_android_demo.repository.model.GetLinkTokenRequest
import com.underdog_tech.pinwheel_android_demo.repository.useCase.GetLinkTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val mode = MutableLiveData("sandbox")
    val orgName = MutableLiveData<String>()
    val routingNumber = MutableLiveData("775041380")
    val accountNumber = MutableLiveData("2305215639")
    val job = MutableLiveData("direct_deposit_switch")
    val accountType = MutableLiveData("checking")
    val skipExitSurvey = MutableLiveData(false)

    fun generateLinkToken(onTokenGenerated: (String) -> Unit) {
        val body = GetLinkTokenRequest(
            job = job.value,
            orgName = orgName.value,
            accountNumber = accountNumber.value,
            routingNumber = routingNumber.value,
            accountType = accountType.value,
            skipExitSurvey = skipExitSurvey.value,
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