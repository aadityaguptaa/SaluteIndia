package com.army.saluteindia.data2.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "house_table")
@Parcelize
data class HOUSES(

    @PrimaryKey(autoGenerate = false)
    var id: String,
    var house: String,
    var floor: String,
    var colour: String,
    var perimeterFence: String,
    var cowshed: String,
    var entryPoints: Int,
    var lat: String,
    var lon: String,
    var property: String,
    var mother_id: Int,
    var father_id: Int,
    var daughter_id: Int,
    var son_id: Int,
    var husband_id: Int,
    var wife_id: Int,
    var btn_id: Int,
    var coy_id: String,
    var village_id: String,
    var mohalla_id: String,
    var husbandName: String,
    var mobileNumber: String,
    var age: String,

): Parcelable