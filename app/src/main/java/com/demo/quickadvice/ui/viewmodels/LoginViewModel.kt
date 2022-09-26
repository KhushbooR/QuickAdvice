package com.demo.quickadvice.ui.viewmodels

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.demo.quickadvice.logger.LogUtil
import com.demo.quickadvice.ui.ErrorConstants
import com.demo.quickadvice.ui.listeners.APIListener

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.java.canonicalName
    var mUserName : String = ""
    var mPassword : String = ""
    var mListener : APIListener? = null

    fun onLoginClicked() {
        LogUtil.debugLog(TAG, "onLoginClicked")

        if (mListener?.isInternetAvailable()!!) {
            mListener?.onStartAPI()
            val email: String = mUserName.trim()
            val password: String = mPassword.trim()

            if (email.isEmpty() || password.isEmpty() || !email.isEmailValid()) {
                if (email.isEmpty()) {
                    mListener?.onError(ErrorConstants.ENTER_EMAIL)
                } else if (!email.isEmailValid()) {
                    mListener?.onError(ErrorConstants.ENTER_EMAIL_VALID)
                }
                if (password.isEmpty()) {
                    mListener?.onError(ErrorConstants.ENTER_PASSWORD)
                }
                return
            }

            getLoginDetails()
        } else {
            mListener?.onError(ErrorConstants.INTERNET_NOT_AVAILABLE)
        }
    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    /**
     * It checks for the login credentials.
     */
    // todo: add login function below
    fun getLoginDetails() {

        mListener?.onError(ErrorConstants.LOGIN_SUCCESS)
    }

}