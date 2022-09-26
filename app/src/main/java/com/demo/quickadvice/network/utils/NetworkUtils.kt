package com.demo.quickadvice.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtils {
    /**
     * Check if network is available
     *
     * @param c - [Context]
     * @return true if network is available
     */
    @Synchronized
    fun isNetworkAvailable(c: Context): Boolean {
        val cm = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    /**
     * @param c [Context]
     */
    @Synchronized
    fun getNetworkType(c: Context): Int {
        val cm = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.type ?: -100
    }

    @Synchronized
    fun getNetworkTypeName(c: Context): String {
        val cm = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork == null) "" else activeNetwork.typeName
    }
}