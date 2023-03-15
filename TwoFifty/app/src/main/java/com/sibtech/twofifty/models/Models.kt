package com.sibtech.twofifty.models

import android.net.wifi.WifiManager.SuggestionConnectionStatusListener
import java.sql.Time
import java.sql.Timestamp
import java.util.Date

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
open class UserModel(
    val id:String="",
    val name: String="",
    val email: String="",
    val userName:String="",
    val publicKey: String="",
)

open class ChatModel(
    val senderId:String="",
    val receiverId: String="",
    val encryptedMsg:String="",
    val forUser:String="",
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

