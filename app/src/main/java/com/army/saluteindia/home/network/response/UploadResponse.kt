package com.army.saluteindia.home.network.response

data class UploadResponse (
    val error: Boolean,
    val message: String,
    val image: String?
)