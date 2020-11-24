package com.underdog_tech.pinwheel_android

import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import com.underdog_tech.pinwheel_android.Pinwheel.CDN_URL
import com.underdog_tech.pinwheel_android.webview.PinwheelWebViewClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class PinwheelFragmentTest {

    private var scenario: FragmentScenario<PinwheelFragment>? = null

    @Before
    fun setupPinwheelFragment() {
        val bundle = Bundle().apply {
            putString("linkToken", "token-string-value")
        }
        this.scenario = launchFragmentInContainer<PinwheelFragment>(bundle)
    }

    @Test(expected = java.lang.IllegalStateException::class)
    fun `check if PinwheelFragment crash without linkToken`() {
        val scenario = launchFragmentInContainer<PinwheelFragment>()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun `check if PinwheelFragment renders properly with linkToken provided`() {
        scenario?.moveToState(Lifecycle.State.RESUMED)
        scenario?.onFragment { fragment ->
            assert(fragment.isResumed)
        } ?: Assert.fail()
    }

    @Test
    fun `check if newInstance puts arguments to PinwheelFragment`() {
        val pinwheelFragment = PinwheelFragment.newInstance("token-string-value")
        assert(pinwheelFragment.arguments?.getString("linkToken").equals("token-string-value"))
    }

    @Test
    fun `test webView configuration after Pinwheel (dot) init`() {
        scenario?.moveToState(Lifecycle.State.RESUMED)
        scenario?.onFragment { fragment ->
            val webView = fragment.view?.findViewById<WebView>(R.id.webView)
            webView?.let {
                assert(it.settings.javaScriptEnabled)
            } ?: Assert.fail()
            assert(webView?.webViewClient is PinwheelWebViewClient)
        }
    }

    @Test
    fun `test loaded URL`() {
        scenario?.moveToState(Lifecycle.State.RESUMED)
        scenario?.onFragment { fragment ->
            val webView = fragment.view?.findViewById<WebView>(R.id.webView)
            webView?.let {
                assert(it.url.equals(CDN_URL))
            } ?: Assert.fail("Web View is null")
        }
    }

    @Test
    fun `test JS interface config`() {
        val webView = WebView(ApplicationProvider.getApplicationContext())

        assert(shadowOf(webView).getJavascriptInterface("Android") == null)

        Pinwheel.init(webView, "linkToken", null)

        assert(shadowOf(webView).getJavascriptInterface("Android") != null)
    }
}