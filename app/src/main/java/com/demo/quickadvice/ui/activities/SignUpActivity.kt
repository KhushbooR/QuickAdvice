package com.demo.quickadvice.ui.activities

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.demo.quickadvice.R
import com.demo.quickadvice.databinding.ActivitySignUpBinding
import com.demo.quickadvice.ui.ErrorConstants
import com.demo.quickadvice.ui.listeners.APIListener
import com.demo.quickadvice.ui.uiutils.HelperClass
import com.demo.quickadvice.ui.viewmodels.SignUpViewModel
import java.lang.Error

class SignUpActivity : BaseActivity(), APIListener {

    private lateinit var mBinding: ActivitySignUpBinding
    lateinit var mSignUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        mSignUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        mSignUpViewModel.mListener = this
        mBinding.signUpViewModel = mSignUpViewModel

        mBinding.signUpEtDob.setOnClickListener {
            showToastMessage("show dob dialog")
        }

        mBinding.signUpCategoryCloseIcon.setOnClickListener {
            showHideCategoryContainer(false)
        }

        setupCategories()
    }

    private fun setupCategories() {
        debugLog("setupCategories()")
        val categories = resources.getStringArray(R.array.area_of_interest_categories)
        val radioGroup = RadioGroup(this)
        for (category in categories) {
            val radioBtn = RadioButton(this)
            radioBtn.highlightColor = resources.getColor(R.color.white)
            radioBtn.setTextColor(resources.getColor(R.color.white))
            radioBtn.buttonTintList = getRadioButtonColor()
            radioBtn.text = category
            radioBtn.id = View.generateViewId()
            radioGroup.addView(radioBtn)
        }
        mBinding.signUpCategoryLl.addView(radioGroup)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checkedBtn : RadioButton = group.findViewById(radioGroup.checkedRadioButtonId)
            mSignUpViewModel.mSelectedCategory = checkedBtn.text.toString()
        }
    }

    private fun getRadioButtonColor(): ColorStateList {
        val states = arrayOf(
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_checked))

        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.white),
            ContextCompat.getColor(this, R.color.white)
        )

        return ColorStateList(states, colors)
    }

    override fun getActivityName(): String = "SignUpActivity"

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
        if (message.equals(ErrorConstants.MOVE_TO_CATEGORY_SCREEN)) {
            showHideCategoryContainer(true)
        } else if (message.equals(ErrorConstants.SIGN_UP_SUCCESS)){
            showToastMessage(message)
            HelperClass.moveToExpertScreen(this)
        }
    }

    override fun <T> onApiCall(response: LiveData<T>) {
        debugLog("onApiCall() with response")
        // todo: add observer for register success response
    }

    fun showHideCategoryContainer(shouldShow: Boolean) {
        if (shouldShow) {
            mBinding.signUpDetailsContainer.visibility = View.GONE
            mBinding.signUpCategoryContainer.visibility = View.VISIBLE
        } else {
            mBinding.signUpDetailsContainer.visibility = View.VISIBLE
            mBinding.signUpCategoryContainer.visibility = View.GONE
        }
    }
}