package com.underdog_tech.pinwheel_android

import android.annotation.SuppressLint
import android.webkit.WebView
import com.underdog_tech.pinwheel_android.webview.PinwheelJavaScriptInterface
import com.underdog_tech.pinwheel_android.webview.PinwheelWebViewClient

object Pinwheel {

    fun init(webView: WebView, linkToken: String, callback: PinwheelEventListener?) {
        val uniqueUserId = UUIDManager(webView.context).uuid

        uniqueUserId?.let {
            configureWebView(webView, linkToken, it, callback)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView(webView: WebView, linkToken: String, uuid: String, callback: PinwheelEventListener?) {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = PinwheelWebViewClient(linkToken, uuid)

        val pinwheelJSInterface = PinwheelJavaScriptInterface(callback)
        pinwheelJSInterface.bind((webView))

        webView.loadUrl(BuildConfig.CDN_URL)
    }
}