package com.army.saluteindia.data.networklogin.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class searchInfo (
    val battalion: String?,
    val village: String?,
    val mohalla: String?,
    val name: String?,
    val fatherName: String?,
    val houseNo: String?,
    val tel: String?,
    val occupation: String?,
    val landArea: String?,
    val floor: String?
    ): Parcelable