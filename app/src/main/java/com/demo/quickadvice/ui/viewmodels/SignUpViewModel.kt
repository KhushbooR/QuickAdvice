package com.demo.quickadvice.ui.viewmodels

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.demo.quickadvice.logger.LogUtil
import com.demo.quickadvice.ui.ErrorConstants
import com.demo.quickadvice.ui.listeners.APIListener

class SignUpViewModel : ViewModel() {

    private val TAG = SignUpViewModel::class.java.canonicalName
    var mName: String = ""
    var mDoB: String = ""
    var mEmail: String = ""
    var mMobileNum: String = ""
    var mPassword: String = ""
    var mConfirmPassword: String = ""
    var mUserIsExpert: Boolean = false
    var mSelectedCategory: String = ""
    var mListener: APIListener? = null

    fun onSignUpClicked() {
        LogUtil.debugLog(TAG, "onSignUpClicked()")

        if (mListener?.isInternetAvailable()!!) {
            mListener?.onStartAPI()
            val email: String = mEmail.trim()
            val password: String = mPassword.trim()
            val name: String = mName.trim()
            val dob: String = mDoB.trim()
            val mobileNum: String = mMobileNum.trim()
            val confirmPassword: String = mConfirmPassword.trim()

            if (email.isEmpty() || password.isEmpty() || !email.isEmailValid()
                || name.isEmpty() || dob.isEmpty() || mobileNum.isEmpty()
                || !password.equals(confirmPassword)
            ) {
                if (name.isEmpty()) {
                    mListener?.onError(ErrorConstants.ENTER_NAME)
                } else if (email.isEmpty()) {
                    mListener?.onError(ErrorConstants.ENTER_EMAIL)
                } else if (!email.isEmailValid()) {
                    mListener?.onError(ErrorConstants.ENTER_EMAIL_VALID)
                } else if (password.isEmpty()) {
                    mListener?.onError(ErrorConstants.ENTER_PASSWORD)
                } else if (!password.equals(confirmPassword)) {
                    mListener?.onError(ErrorConstants.PASSWORDS_DO_NOT_MATCH)
                } else if (dob.isEmpty()) {
                    mListener?.onError(ErrorConstants.SELECT_DOB)
                } else if (mobileNum.isEmpty()) {
                    mListener?.onError(ErrorConstants.ENTER_MOBILE_NUM)
                }
                return
            }

            startSignUpProcess()

        } else {
            mListener?.onError(ErrorConstants.INTERNET_NOT_AVAILABLE)
        }
    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun startSignUpProcess() {
        // todo: add azure impl for sign up
        if (mUserIsExpert && mSelectedCategory.isEmpty()) {
            mListener?.onError(ErrorConstants.MOVE_TO_CATEGORY_SCREEN)
        } else {
            mListener?.onError(ErrorConstants.SIGN_UP_SUCCESS)
        }
    }

}