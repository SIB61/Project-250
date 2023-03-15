package com.sibtech.twofifty.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sibtech.twofifty.chatMessages
import com.sibtech.twofifty.ui.components.ChatList
import com.sibtech.twofifty.ui.components.ProfileAvatar
import com.sibtech.twofifty.userList
import com.sibtech.twofifty.viewmodels.ConnectionsViewModel

@Composable
fun Connections(navController: NavHostController,parentNavigate:(route:String)->Unit) {
    val vm = viewModel<ConnectionsViewModel>()
    vm.getConnections(LocalContext.current)
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(vm.connections.value) { connection ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    parentNavigate("chat/"+connection.userName+"/"+connection.name+"/"+connection.id)
                }
                .padding(10.dp)) {
                ProfileAvatar(
                    imageUrl = chatMessages[2].senderImageUrl,
                    modifier = Modifier.size(50.dp)
                )
                Column(modifier = Modifier.fillMaxSize().padding(6.dp), verticalArrangement = Arrangement.Center) {
                    Text(text = connection.name, fontSize = 24.sp)
                    Text(text = "@"+ connection.userName, fontSize = 20.sp, fontFamily = FontFamily.Monospace , fontWeight = FontWeight.Light)
                }
            }
        }
    }
}