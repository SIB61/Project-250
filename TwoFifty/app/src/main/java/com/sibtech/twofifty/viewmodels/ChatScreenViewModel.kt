package com.sibtech.twofifty.viewmodels
import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sibtech.twofifty.lib.encryptMessage
import com.sibtech.twofifty.lib.encryptRSA
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.models.ChatModel
import com.sibtech.twofifty.models.UserModel
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.Dispatchers
import okhttp3.*
import java.net.URISyntaxException
import java.security.PublicKey
import kotlin.math.log


class ChatScreenViewModel:ViewModel() {

    val messageText = mutableStateOf("")
    val messageList = mutableStateListOf<ChatModel>()
    val messages = mutableStateOf(mutableListOf<ChatModel>())
    val connectedUser = mutableStateOf<SearchResponse>(SearchResponse())

    var socket: Socket?=null
    val isLoading = mutableStateOf(false)

    fun start(ctx:Context){
        try {
            val options = IO.Options()
            options.forceNew = true
            options.reconnection = true
            options.query = "custom_id="+ getSharedPrep(ctx).getString("id","")!!
            socket = IO.socket("http://192.168.0.111:3000",options)

            socket?.on("connect"){
                Log.d("socket", "start: ")
            }

            socket?.on("sendMessage"){
                var chatModel = ChatModel(
                    senderId = it[0] as String,
                    receiverId = it[1] as String,
                    encryptedMsg = it[2] as String,
                    forUser = it[3] as String
                )
                messageList.add(chatModel)
            }

            socket?.connect()
        }catch (err:URISyntaxException){
            Log.d("socket", "start: "+err)
        }
    }

    fun sendMessage(context:Context, receiverId:String, receiverPublicKey: String){
        val sharedPreferences = getSharedPrep(context)
        val senderId = sharedPreferences.getString("id","")!!
        val myPublicKeyStr = sharedPreferences.getString("publicKey","")!!
        val chatModelForMe =  ChatModel(senderId = senderId,receiverId=receiverId, encryptedMsg = messageText.value, forUser = senderId)
        val chatModelForOther = ChatModel(senderId=senderId,receiverId=receiverId, encryptedMsg = messageText.value, forUser = receiverId)
        socket?.emit("sendMessage",chatModelForMe.senderId,chatModelForMe.receiverId,chatModelForMe.encryptedMsg,chatModelForMe.forUser)
        socket?.emit("sendMessage",chatModelForOther.senderId,chatModelForMe.receiverId,chatModelForOther.encryptedMsg,chatModelForOther.forUser)
    }
}