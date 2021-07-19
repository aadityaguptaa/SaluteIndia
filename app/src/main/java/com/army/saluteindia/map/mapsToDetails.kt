package com.army.saluteindia.map

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class mapsToDetails (
    var village: String,
    var mohalla: String,
    var houseNo: String,
    var name: String,
    var fatherName: String,
    var surname: String,
    var age: Int,
    var mobileNumber: String,
    var occupation: String,
    var landArea: String,
    var houseType: String,
    var colour: String,
    var shed: String,
    var floor: String
        ): Parcelable