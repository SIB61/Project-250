package com.sibtech.twofifty.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sibtech.twofifty.chatMessages
import com.sibtech.twofifty.ui.components.ChatList

@Composable
fun Chats(navController: NavHostController) {
//   Text(text = "chats")
    ChatList(messages = chatMessages)
}