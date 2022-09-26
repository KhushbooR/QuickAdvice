package com.demo.quickadvice.ui.models

import android.os.Parcelable
import com.demo.quickadvice.ui.interfaces.ListAdapterItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExpertData(val name: String, val expertId: String, val specializedIn : String) : Parcelable, ListAdapterItem {
    override val data: String
        get() = name
}
