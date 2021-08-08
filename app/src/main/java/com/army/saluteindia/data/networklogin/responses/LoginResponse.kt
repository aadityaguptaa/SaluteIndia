package com.army.saluteindia.data.networklogin.responses

data class LoginResponse(
    val `data`: Data,
    val token: String,
    val user: User
)