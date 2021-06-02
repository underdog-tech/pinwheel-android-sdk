package com.underdog_tech.pinwheel_android.webview

import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.underdog_tech.pinwheel_android.BuildConfig
import com.underdog_tech.pinwheel_android.model.ClientMetadata

class PinwheelWebViewClient(
    private val linkToken: String,
    private val uuid: String,
    private val timestamp: Long,
    private val metaData: ClientMetadata
): WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        view?.let { injectJS(it) }
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(request.url.toString()))
        view.context.startActivity(i)
        return true
    }

    private fun injectJS(view: WebView) {
        val version = BuildConfig.LIBRARY_VERSION.split(".")
        val script =
            """try {
                  window.addEventListener('message', event => {
                    Android.onLinkMessage(JSON.stringify(event.data))
                 });
                 window.postMessage(
                            {
                                type: 'PINWHEEL_INIT',
                                payload: {
                                    linkToken: '$linkToken',
                                    uniqueUserId: '$uuid',
                                    initializationTimestamp: $timestamp,
                                    sdk: 'android',
                                    platform: 'android',
                                    deviceMetadata: {
                                        os: ${metaData.os},
                                        manufacturer: '${metaData.manufacturer}',
                                        model: '${metaData.model}',
                                        product: '${metaData.product}',
                                        device: '${metaData.device}',
                                        hardware: '${metaData.hardware}',
                                    },
                                    version: {
                                        major: ${version[0]},
                                        ${if (version.size > 1) { "minor: " + version[1] } else { "" }},
                                        ${if (version.size > 2) { "patch: " + version[2] } else { "" }},
                                    },
                                    initializationOptions: {
                                        hasOnSuccess: ${metaData.hasOnSuccess},
                                        hasOnEvent: ${metaData.hasOnEvent},
                                        hasOnExit: ${metaData.hasOnExit},
                                        hasOnError: ${metaData.hasOnError},
                                        hasOnLogin: ${metaData.hasOnLogin},
                                    }
                                 }
                            }
                  );
                  } catch(err) {
                    console.error(err);
                  }"""

        view.loadUrl("javascript:(function f() {$script})()")
    }
}