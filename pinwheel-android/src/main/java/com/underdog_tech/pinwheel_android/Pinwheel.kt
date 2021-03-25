package com.underdog_tech.pinwheel_android

import android.annotation.SuppressLint
import android.os.Build
import android.webkit.WebView
import com.underdog_tech.pinwheel_android.model.ClientMetadata
import com.underdog_tech.pinwheel_android.webview.PinwheelJavaScriptInterface
import com.underdog_tech.pinwheel_android.webview.PinwheelWebViewClient
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.memberFunctions

object Pinwheel {

    const val CDN_URL= "https://cdn.getpinwheel.com/link-v2.2.1.html"

    fun init(webView: WebView, linkToken: String, callback: PinwheelEventListener?) {
        val timestamp = getUnixTimestamp()
        val uniqueUserId = UUIDManager(webView.context).uuid
        uniqueUserId?.let {
            configureWebView(webView, linkToken, it, timestamp, callback)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView(webView: WebView, linkToken: String, uuid: String, timestamp: Long, callback: PinwheelEventListener?) {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = PinwheelWebViewClient(linkToken, uuid, timestamp, getClientMetadata(callback))
        val pinwheelJSInterface = PinwheelJavaScriptInterface(callback)
        pinwheelJSInterface.bind((webView))

        webView.loadUrl(CDN_URL)
    }

    private fun getClientMetadata(callback: PinwheelEventListener?) = ClientMetadata(
        Build.VERSION.SDK_INT,
        Build.MANUFACTURER,
        Build.MODEL,
        Build.PRODUCT,
        Build.DEVICE,
        Build.HARDWARE,
        callback != null && overridesMethod(callback::class, "onLogin"),
        callback != null && overridesMethod(callback::class, "onSuccess"),
        callback != null && overridesMethod(callback::class, "onError"),
        callback != null && overridesMethod(callback::class, "onExit"),
        callback != null && overridesMethod(callback::class, "onEvent"),
    )

    private fun getUnixTimestamp(): Long {
        return System.currentTimeMillis()
    }

    private fun overridesMethod(cls: KClass<out PinwheelEventListener>, methodName: String): Boolean {
        return cls.memberFunctions.first { it.name == methodName } in cls.declaredFunctions
    }
}
