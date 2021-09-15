package com.army.saluteindia.network.coys

import com.google.gson.annotations.SerializedName

data class CoyItem (
    var _id: String,
    @SerializedName("villagesCount")
    var villagesCount: Int,
    @SerializedName("mohallasCount")
    var mohallasCount: Int,
    @SerializedName("housesCount")
    var housesCount: Int

        )