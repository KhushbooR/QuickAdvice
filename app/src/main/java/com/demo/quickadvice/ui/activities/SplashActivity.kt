package com.demo.quickadvice.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.demo.quickadvice.R
import com.demo.quickadvice.ui.uiutils.HelperClass

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            HelperClass.moveToLoginScreen(this)
            finish()
        }, 2500)

    }
}