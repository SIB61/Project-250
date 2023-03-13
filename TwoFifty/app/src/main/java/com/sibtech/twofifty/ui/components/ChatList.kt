package com.sibtech.twofifty.ui.components
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.sibtech.twofifty.models.ChatMessage

@Composable
fun ChatList(messages: List<ChatMessage>) {
    LazyColumn {
        items(messages) { message ->
            ChatMessageItem(message = message)
        }
    }
}
