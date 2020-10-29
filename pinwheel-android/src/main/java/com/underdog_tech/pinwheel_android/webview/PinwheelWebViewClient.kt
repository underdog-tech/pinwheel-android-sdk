package com.underdog_tech.pinwheel_android.webview

import android.webkit.WebView
import android.webkit.WebViewClient

class PinwheelWebViewClient(private val linkToken: String, private val uuid: String): WebViewClient() {

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