package com.underdog_tech.pinwheel_android.webview

import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.underdog_tech.pinwheel_android.PinwheelEventListener
import com.underdog_tech.pinwheel_android.model.*

class PinwheelJavaScriptInterface(private val pinwheelEventListener: PinwheelEventListener?) {

    private val gson = Gson()

    @JavascriptInterface
    fun onLinkMessage(message: String) {
        pinwheelEventListener?.let {
            val messageJson = gson.fromJson(message, JsonObject::class.java)
            val type: String = messageJson.get("type").asString

            if (type == EVENT_MESSAGE) {
                val eventName: String = messageJson.get("eventName").asString
                val payload: JsonObject = messageJson.getAsJsonObject("payload")

                when (eventName) {
                    "open" -> {
                        it.onEvent(PinwheelEventType.OPEN, null)
                    }
                    "select_employer" -> {
                        it.onEvent(
                            PinwheelEventType.SELECT_EMPLOYER,
                            gson.fromJson(payload, PinwheelSelectedEmployerPayload::class.java)
                        )
                    }
                    "select_platform" -> {
                        it.onEvent(
                            PinwheelEventType.SELECT_PLATFORM,
                            gson.fromJson(payload, PinwheelSelectedPlatformPayload::class.java)
                        )
                    }
                    "input_amount" -> {
                        it.onEvent(PinwheelEventType.INPUT_AMOUNT, gson.fromJson(
                            payload,
                            PinwheelAmount::class.java)
                        )
                    }
                    "input_allocation" -> {
                        it.onEvent(PinwheelEventType.INPUT_ALLOCATION, gson.fromJson(
                            payload,
                            PinwheelInputAmountPayload::class.java
                        ))
                    }
                    "input_required" -> {
                        it.onEvent(PinwheelEventType.INPUT_REQUIRED, null)
                    }
                    "exit" -> {
                        var error: PinwheelError? = null;

                        if (payload.has("error")) {
                            error = gson.fromJson(payload.get("error"), PinwheelError::class.java)
                        }

                        it.onEvent(PinwheelEventType.EXIT, error)
                        it.onExit(error)
                    }
                    "success" -> {
                        val result: PinwheelResult = gson.fromJson(
                            payload,
                            PinwheelResult::class.java
                        )
                        it.onEvent(PinwheelEventType.SUCCESS, result)
                        it.onSuccess(result)
                    }
                    "login" -> {
                        val result: PinwheelLoginPayload = gson.fromJson(
                            payload,
                            PinwheelLoginPayload::class.java
                        )
                        it.onEvent(PinwheelEventType.LOGIN, result)
                        it.onLogin(result)
                    }
                    "login_attempt" -> {
                        val result: PinwheelLoginAttemptPayload = gson.fromJson(
                            payload,
                            PinwheelLoginAttemptPayload::class.java
                        )
                        it.onEvent(PinwheelEventType.LOGIN_ATTEMPT, result)
                        it.onLoginAttempt(result)
                    }
                    "error" -> {
                        val error: PinwheelError = gson.fromJson(
                            payload,
                            PinwheelError::class.java
                        )
                        it.onEvent(PinwheelEventType.ERROR, error)
                        it.onError(error)
                    }
                    "incorrect_platform_given" -> {
                        it.onEvent(PinwheelEventType.INCORRECT_PLATFORM_GIVEN, null)
                    }
                }
            }
        }
    }

    fun bind(webView: WebView) {
        webView.addJavascriptInterface(this, "Android")
    }
}