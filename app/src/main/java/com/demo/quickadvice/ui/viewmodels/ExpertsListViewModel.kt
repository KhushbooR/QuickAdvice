package com.demo.quickadvice.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.quickadvice.ui.ErrorConstants
import com.demo.quickadvice.ui.listeners.APIListener
import com.demo.quickadvice.ui.listeners.ExpertItemListener
import com.demo.quickadvice.ui.models.ExpertData

class ExpertsListViewModel : ViewModel(), ExpertItemListener {

    private val expertListMutable = MutableLiveData<List<ExpertData>>()
    val expertList : LiveData<List<ExpertData>> = expertListMutable

    val tempExpertList = mutableListOf<ExpertData>()

    var expertToConnect: ExpertData? = null
    var mListener: APIListener? = null

    fun addUsersForDemo() {
        if (tempExpertList.isNotEmpty())
            tempExpertList.clear()
        for(i in 0..9) {
            val userName = "User$i"
            val specialized = "User selected category"
            val expertData = ExpertData (name = userName, expertId = i.toString(), specializedIn = specialized)
            tempExpertList.add(expertData)
        }
    }

    override fun onConnectWithExpertClick(expertData: ExpertData) {
        expertToConnect = expertData
        mListener?.onError(ErrorConstants.CONNECT_WITH_EXPERT)
    }
}