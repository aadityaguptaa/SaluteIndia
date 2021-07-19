package com.army.saluteindia.data2.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "house_table")
data class HOUSES(

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var house: String,
    var floor: String,
    var colour: String,
    var perimeterFence: String,
    var cowshed: String,
    var entryPoints: Int,
    var lat: String,
    var lon: String,
    var property: Int,
    var mother_id: Int,
    var father_id: Int,
    var daughter_id: Int,
    var son_id: Int,
    var husband_id: Int,
    var wife_id: Int,
    var btn_id: Int,
    var coy_id: String,
    var village_id: String,
    var mohalla_id: String

)