package com.demo.quickadvice.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.demo.quickadvice.R
import com.demo.quickadvice.databinding.ActivityLoginBinding
import com.demo.quickadvice.ui.ErrorConstants
import com.demo.quickadvice.ui.listeners.APIListener
import com.demo.quickadvice.ui.uiutils.HelperClass
import com.demo.quickadvice.ui.viewmodels.LoginViewModel

class LoginActivity : BaseActivity(), APIListener {

    private lateinit var mBinding: ActivityLoginBinding
    lateinit var mLoginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mLoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        mLoginViewModel.mListener = this
        mBinding.loginViewModel = mLoginViewModel

        mBinding.loginTvSignup.setOnClickListener {
            showToastMessage("go to sign up")
            HelperClass.moveToSignUpScreen(this)
        }
    }

    override fun getActivityName(): String = "LoginActivity"

    override fun onStartAPI() {
        debugLog("onStartApi()")
        mBinding.progress.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        debugLog("onSuccess()")
        mBinding.progress.visibility = View.GONE
    }

    override fun onError(message: String) {
        debugLog("onError() with message: $message")
        mBinding.progress.visibility = View.GONE
        if (message.equals(ErrorConstants.LOGIN_SUCCESS)) {
            showToastMessage(message)
            HelperClass.moveToExpertScreen(this)
        }
    }

    override fun <T> onApiCall(response: LiveData<T>) {
        debugLog("onApiCall() with response")
        // todo: add observer for login success response
    }

}