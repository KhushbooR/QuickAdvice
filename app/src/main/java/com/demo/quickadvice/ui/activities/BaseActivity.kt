package com.demo.quickadvice.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.quickadvice.logger.LogUtil
import com.demo.quickadvice.network.utils.NetworkUtils
import com.demo.quickadvice.network.utils.SSLUtils
import javax.net.ssl.SSLContext

abstract class BaseActivity : AppCompatActivity() {

    lateinit var TAG : String

    abstract fun getActivityName() : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = getActivityName()
    }

    fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun isInternetAvailable(): Boolean {
        return NetworkUtils.isNetworkAvailable(this)
    }

    fun getSSlContext(): SSLContext {
        val sslContext: SSLContext =
            SSLUtils.getSslContextForCertificateFile(this, "servercerts.pem")

        return sslContext
    }

    fun debugLog(message: String) {
        LogUtil.debugLog(TAG, message)
    }

}