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
    val profileImageUrl: String,
    val publicKey: String = ""
)
data class Message(
    val id:String,
    val encryptedMessage:String,
    val senderId:String,
    val receiverId:String,
)
