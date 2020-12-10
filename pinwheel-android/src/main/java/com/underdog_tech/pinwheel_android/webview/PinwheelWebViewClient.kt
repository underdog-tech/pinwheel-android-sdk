package com.underdog_tech.pinwheel_android.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import com.underdog_tech.pinwheel_android.model.DeviceMetadata

class PinwheelWebViewClient(private val linkToken: String, private val uuid: String, private val timestamp: Long, private val metaData: DeviceMetadata): WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        view?.let { injectJS(it) }
    }

    private fun injectJS(view: WebView) {
        val script =
            """try {
                  window.addEventListener('message', event => {
                    Android.onLinkMessage(JSON.stringify(event.data))
                 });
                 window.postMessage(
                            {
                                type: 'PINWHEEL_INIT',
                                payload: {
                                    fullScreen: true,
                                    sdk: 'android',
                                    deviceMetadata: {
                                        os: ${metaData.os},
                                        manufacturer: '${metaData.manufacturer}',
                                        model: '${metaData.model}',
                                        product: '${metaData.product}',
                                        device: '${metaData.device}',
                                        hardware: '${metaData.hardware}',
                                    },
                                    initializationTimestamp: $timestamp,
                                    linkToken: '$linkToken',
                                    uniqueUserId: '$uuid',
                                 }
                            }
                  );
                  } catch(err) {
                    console.error(err);
                  }"""

        view.loadUrl("javascript:(function f() {$script})()")
    }
}