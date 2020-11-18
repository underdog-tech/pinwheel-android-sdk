package com.underdog_tech.pinwheel_android_demo

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.underdog_tech.pinwheel_android.UUIDManager
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UUIDManagerTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val uuidManager = UUIDManager(context)

    @Test
    fun `check if once generated uuid returns the same value every time`() {
        val firstAttempt = uuidManager.uuid
        val secondAttempt = uuidManager.uuid

        assert(firstAttempt.equals(secondAttempt))
    }
}