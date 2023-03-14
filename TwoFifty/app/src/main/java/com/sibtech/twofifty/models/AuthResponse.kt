package com.sibtech.twofifty.models
data class AuthResponse(
    val accessToken: String,
    val email: String,
    val id: String,
    val name: String,
    val userName: String
)