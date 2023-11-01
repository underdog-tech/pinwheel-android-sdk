package com.underdog_tech.pinwheel_android

import android.annotation.SuppressLint
import android.os.Build
import android.webkit.DownloadListener
import android.webkit.WebView
import android.widget.Toast
import com.underdog_tech.pinwheel_android.model.ClientMetadata
import com.underdog_tech.pinwheel_android.webview.PinwheelJavaScriptInterface
import com.underdog_tech.pinwheel_android.webview.PinwheelWebViewClient


object Pinwheel {

    const val CDN_URL= "https://cdn.getpinwheel.com/link-v2.3.0.html"

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
        webView.settings.domStorageEnabled = true
        webView.webViewClient = PinwheelWebViewClient(linkToken, uuid, timestamp, getClientMetadata())

        val blobDownloadJavaScriptInterface = BlobDownloadJavaScriptInterface(webView.context)
        blobDownloadJavaScriptInterface.bind(webView)
        val pinwheelJSInterface = PinwheelJavaScriptInterface(callback)
        pinwheelJSInterface.bind((webView))
        webView.setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            if (url.startsWith("blob:")) {
                webView.evaluateJavascript(blobDownloadJavaScriptInterface.getBase64StringFromBlobUrl(url), null)
            }
            Toast.makeText(webView.context, "Form downloaded", Toast.LENGTH_LONG).show();
        })

        webView.loadUrl(CDN_URL)
    }

    private fun getClientMetadata() = ClientMetadata(
        Build.VERSION.SDK_INT,
        Build.MANUFACTURER,
        Build.MODEL,
        Build.PRODUCT,
        Build.DEVICE,
        Build.HARDWARE,
    )

    private fun getUnixTimestamp(): Long {
        return System.currentTimeMillis()
    }
}
