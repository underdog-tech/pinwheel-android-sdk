package com.underdog_tech.pinwheel_android.webview

import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.google.gson.Gson
import com.underdog_tech.pinwheel_android.PinwheelEventListener
import com.underdog_tech.pinwheel_android.model.PinwheelActionEvent
import com.underdog_tech.pinwheel_android.model.PinwheelEventType
import com.underdog_tech.pinwheel_android.model.PinwheelExitEvent
import com.underdog_tech.pinwheel_android.model.PinwheelSuccessEvent
import org.json.JSONObject

class PinwheelJavaScriptInterface(private val pinwheelEventListener: PinwheelEventListener?) {

    private val gson = Gson()

    @JavascriptInterface
    fun onLinkMessage(message: String) {
        pinwheelEventListener?.let {
            val messageJSON = JSONObject(message)
            when (messageJSON.getString("type")) {
                PinwheelEventType.PINWHEEL_EVENT -> sendPinwheelActionEvent(message, it)
                PinwheelEventType.PINWHEEL_SUCCESS -> sendPinwheelSuccessEvent(message, it)
                PinwheelEventType.PINWHEEL_MODAL_CLOSE -> sendPinwheelExitEvent(message, it)
            }
        }
    }

    private fun sendPinwheelActionEvent(message: String, callback: PinwheelEventListener) {
        val actionEvent = gson.fromJson(message, PinwheelActionEvent::class.java)
        callback.onEvent(actionEvent)
    }

    private fun sendPinwheelSuccessEvent(message: String, callback: PinwheelEventListener) {
        val successEvent = gson.fromJson(message, PinwheelSuccessEvent::class.java)
        callback.onSuccess(successEvent)
    }

    private fun sendPinwheelExitEvent(message: String, callback: PinwheelEventListener) {
        val exitEvent = gson.fromJson(message, PinwheelExitEvent::class.java)
        callback.onExit(exitEvent)
    }

    fun bind(webView: WebView) {
        webView.addJavascriptInterface(this, "Android")
    }
}