package com.army.saluteindia.network.villages

import com.google.gson.annotations.SerializedName

data class Data(
    val _id: String,
    @SerializedName("houseCount")
    val houseCount: Int,
    @SerializedName("mohallaCount")
    val mohallaCount: Int,

    val coy: String
)