package com.demo.quickadvice.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.demo.quickadvice.R
import com.demo.quickadvice.databinding.ActivityExpertsListBinding
import com.demo.quickadvice.ui.ErrorConstants
import com.demo.quickadvice.ui.StringConstants
import com.demo.quickadvice.ui.adapters.ExpertListAdapter
import com.demo.quickadvice.ui.listeners.APIListener
import com.demo.quickadvice.ui.models.ExpertData
import com.demo.quickadvice.ui.uiutils.HelperClass
import com.demo.quickadvice.ui.viewmodels.ExpertsListViewModel

class ExpertsListActivity : BaseActivity(), APIListener {

    private lateinit var mBinding: ActivityExpertsListBinding
    lateinit var mExpertViewModel: ExpertsListViewModel
    var targetExpertUser : ExpertData? = null

    override fun getActivityName(): String = "ExpertsListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_experts_list)
        mExpertViewModel = ViewModelProvider(this).get(ExpertsListViewModel::class.java)

        mExpertViewModel.mListener = this
        mExpertViewModel.addUsersForDemo()

        mBinding.expertViewModel = mExpertViewModel
        mBinding.expertAdapter = ExpertListAdapter(mExpertViewModel.tempExpertList, mExpertViewModel)

        setUpSearchBox()

        mBinding.expertProfileIv.setOnClickListener {
            // move to profile screen
            showToastMessage("Profile screen to be added in update")
        }
    }

    override fun onResume() {
        super.onResume()
        mBinding.expertSearchTv.setText("")
    }

    private fun setUpSearchBox() {
        debugLog("setUpSearchBox")
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item,
            resources.getStringArray(R.array.area_of_interest_categories_hints))
        mBinding.expertSearchTv.threshold = 1
        mBinding.expertSearchTv.setAdapter(arrayAdapter)
    }
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
        showToastMessage(message)
        if (message.equals(ErrorConstants.CONNECT_WITH_EXPERT)) {
            // move to chat screen
            HelperClass.moveToChatScreen(this, mExpertViewModel.expertToConnect)
        }
    }

    override fun <T> onApiCall(response: LiveData<T>) {
        debugLog("onApiCall() with response")
        // todo: add observer for register success response
    }
}