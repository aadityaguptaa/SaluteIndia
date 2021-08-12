package com.army.saluteindia.data.networklogin.searchResponses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class parcelData (
    val _id: String?,
    val age: String,
    val hid: String?,
    val name: String?,
    val occupation: String?,
    val sex: String?,
    val tel: String?
        ): Parcelable