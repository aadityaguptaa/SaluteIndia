package com.army.saluteindia.network.mohallas

import com.google.gson.annotations.SerializedName

data class Data(
    val _id: String,
    @SerializedName("houseCount")
    val houseCount: Int,
    val village: String
)