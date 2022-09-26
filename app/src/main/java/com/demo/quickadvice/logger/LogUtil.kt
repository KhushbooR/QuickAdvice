package com.demo.quickadvice.logger

import android.util.Log

class LogUtil {

    companion object {
        fun debugLog(tag: String, message: String) {
            Log.d(tag, message)
        }

        fun errorLog(tag: String, error: String) {
            Log.e(tag, error)
        }

        fun warnLog(tag: String, warning: String) {
            Log.w(tag, warning)
        }

    }
}