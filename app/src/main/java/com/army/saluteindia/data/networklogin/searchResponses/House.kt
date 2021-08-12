package com.army.saluteindia.data.networklogin.searchResponses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class House(
    val GR: String?,
    val _id: String?,
    val btn: String?,
    val colour: String?,
    val cowshed: Boolean?,
    val coy: String?,
    val daughter: String?,
    val entryPoints: String?,
    val father: String?,
    val floor: String?,
    val geo: @RawValue Geo?,
    val house: String?,
    val husband: String?,
    val mohalla: String?,
    val mother: String?,
    val nRooms: String?,
    val perimeterfence: Boolean?,
    val property: String?,
    val son: String?,
    val village: String?,
    val wife: String?
): Parcelable