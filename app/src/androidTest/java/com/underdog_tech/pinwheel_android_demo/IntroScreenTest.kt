package com.underdog_tech.pinwheel_android_demo

import android.os.SystemClock
import android.util.Log
import android.webkit.WebView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.webClick
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.junit.*
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule



@RunWith(AndroidJUnit4::class)
class IntroScreenTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    private val device: UiDevice

    init {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)
    }

    @Test
    fun validateClose() {
        val timeOut: Long = 1000 * 60;

        onView(withText("Create Link Token"))
            .perform(click())

        device.wait(Until.findObject(By.clazz(WebView::class.java)), timeOut)

//        SystemClock.sleep(5000);

        onWebView(withId(R.id.webView))
            .forceJavascriptEnabled()
            .withElement(findElement(Locator.XPATH, "//button[contains(@aria-label,\"Close Button\")]")) // similar to onView(withId(...))
            .withNoTimeout()
            .perform(webClick()) // Similar to perform(click())

        onWebView(withId(R.id.webView))
            .forceJavascriptEnabled()
            .withElement(findElement(Locator.XPATH, "//button[contains(@aria-label,\"Exit Confirmation Button\")]")) // similar to onView(withId(...))
            .withNoTimeout()
            .perform(webClick()) // Similar to perform(click())
    }

    /**
     * Run before the method with @Test annotation
     */
    @Before
    fun before() {
        Log.d(TAG, "Before")
    }

    /**
     * Run after each method with @Test annotation
     */
    @After
    fun after() {
        Log.d(TAG, "After")
    }

    companion object {
        private const val TAG = "UiAutomatorExample"

        /**
         * Run once before other methods from [UiAutomatorOrder] class
         */
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            Log.d(TAG, "Before Class")
        }

        /**
         * Run once after other methods from [UiAutomatorOrder] class
         */
        @JvmStatic
        @AfterClass
        fun afterClass() {
            Log.d(TAG, "After Class")
        }
    }
}