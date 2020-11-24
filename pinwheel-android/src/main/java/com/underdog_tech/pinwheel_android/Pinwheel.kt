package com.underdog_tech.pinwheel_android

import android.annotation.SuppressLint
import android.webkit.WebView
import com.underdog_tech.pinwheel_android.webview.PinwheelJavaScriptInterface
import com.underdog_tech.pinwheel_android.webview.PinwheelWebViewClient

object Pinwheel {

    const val CDN_URL= "https://cdn.getpinwheel.com/link-v2.html"

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

        webView.loadUrl(CDN_URL)
    }
}