package com.sibtech.twofifty.viewmodels
import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sibtech.twofifty.lib.encryptRSA
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.models.ChatModel
import com.sibtech.twofifty.models.UserModel
import io.socket.client.IO
import io.socket.client.Socket
import okhttp3.*
import java.net.URISyntaxException
import java.security.PublicKey
import kotlin.math.log


class ChatScreenViewModel:ViewModel() {

    val messageText = mutableStateOf("")
    val messages = mutableStateOf(mutableListOf<ChatModel>())
    val connectedUser = mutableStateOf<SearchResponse>(SearchResponse())
    var ws:WebSocket? = null

    var socket:Socket?=null

    fun start(){
        try {
           socket = IO.socket("http://192.168.0.139:3000")
            socket?.on("connect"){
                Log.d("socket", "start: ")
              messages.value.add(it[0] as ChatModel)
            }
            socket?.connect()
        }catch (err:URISyntaxException){
            Log.d("socket", "start: "+err)
        }

    }



//    fun start(){
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("ws://192.168.0.139:3000/")
//            .build()
//        ws = client.newWebSocket(request, object : WebSocketListener() {
//            override fun onOpen(webSocket: WebSocket, response: Response) {
//                // Connection opened
//                Log.d("tag","connected ws")
//            }
//
//            override fun onMessage(webSocket: WebSocket, text: String) {
//                // New message received
//
//            }
//
//            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
//                // Connection closing
//            }
//
//            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//                // Connection failed
//            }
//        })
//    }

    fun sendMessage(context:Context, receiverId:String, receiverPublicKey: String){
        val sharedPreferences = getSharedPrep(context)
        val senderId = sharedPreferences.getString("id","")!!
        val myPublicKeyStr = sharedPreferences.getString("publicKey","")!!
        val chatModelForMe =  ChatModel(senderId = senderId,receiverId=receiverId, encryptedMsg = encryptRSA(,))
        socket?.send("sendMessage",chatModelForMe)
    }
}