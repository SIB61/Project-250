package com.sibtech.twofifty.models

data class ChatMessage(
    val sender: String,
    val message: String,
    val timestamp: Long,
    val senderImageUrl: String
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val userId:String="",
    val profileImageUrl: String = "",
    val publicKey: String = ""
)

data class LoginRequest(
    var email:String,
    var password:String,
    var publicKey: String
)

data class RegistrationRequest(
    var email:String,
    var password: String,
    var name: String,
    var userName: String,
    var publicKey: String
)

data class Message(
    val id:String,
    val encryptedMessage:String,
    val senderId:String,
    val receiverId:String,
)
