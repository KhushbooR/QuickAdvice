package com.demo.quickadvice.ui.listeners

import androidx.lifecycle.LiveData
import javax.net.ssl.SSLContext

interface APIListener {

    fun onStartAPI()
    fun onSuccess()
    fun onError(message: String)
    fun <T> onApiCall(response: LiveData<T>)

    fun isInternetAvailable(): Boolean

    fun getSSlContext(): SSLContext

}