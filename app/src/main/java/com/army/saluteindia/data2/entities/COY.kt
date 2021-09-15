package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coy_table")
data class COY (

    @PrimaryKey(autoGenerate = false)
    var _id: String,
    @SerializedName("villagesCount")
    var villagesCount: Int,
    @SerializedName("mohallasCount")
    var mohallasCount: Int,
    @SerializedName("housesCount")
    var housesCount: Int

)