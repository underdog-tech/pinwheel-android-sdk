package com.underdog_tech.pinwheel_android_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.underdog_tech.pinwheel_android_demo.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.main_activity))
    }
}