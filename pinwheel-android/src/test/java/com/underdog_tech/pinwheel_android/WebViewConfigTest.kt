package com.underdog_tech.pinwheel_android

import android.os.Build
import android.webkit.WebView
import androidx.test.core.app.ApplicationProvider
import com.underdog_tech.pinwheel_android.Pinwheel.CDN_URL
import com.underdog_tech.pinwheel_android.webview.PinwheelWebViewClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class WebViewConfigTest {

    private lateinit var webView: WebView

    @Before
    fun initWebView() {
        webView = WebView(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `test webView configuration after Pinwheel (dot) init`() {
        Pinwheel.init(webView, "linkToken", null)

        assert(webView.settings.javaScriptEnabled)
        assert(webView.webViewClient is PinwheelWebViewClient)
    }

    @Test
    fun `test loaded URL`() {
        Pinwheel.init(webView, "linkToken", null)
        assert(webView.url.equals(CDN_URL))
    }

    @Test
    fun `test JS interface config`() {
        assert(Shadows.shadowOf(webView).getJavascriptInterface("Android") == null)

        Pinwheel.init(webView, "linkToken", null)

        assert(Shadows.shadowOf(webView).getJavascriptInterface("Android") != null)
    }
}