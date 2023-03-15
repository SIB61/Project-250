package com.sibtech.twofifty.data
import okhttp3.*

val client = OkHttpClient()
val request = Request.Builder()
    .url("wss://example.com/ws")
    .build()

val webSocket = client.newWebSocket(request, object : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        // Connection opened
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        // New message received
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        // Connection closing
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        // Connection failed
    }
})

