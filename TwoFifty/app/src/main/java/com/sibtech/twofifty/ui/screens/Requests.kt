package com.sibtech.twofifty.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sibtech.twofifty.chatMessages
import com.sibtech.twofifty.ui.components.ProfileAvatar
import com.sibtech.twofifty.ui.components.UserListItem
import com.sibtech.twofifty.userList
import com.sibtech.twofifty.viewmodels.RequestViewModel
import com.sibtech.twofifty.viewmodels.RequestsResponseModel

@Composable
fun Requests(navHostController: NavHostController, parentNavigate:(route:String)->Unit) {
   val vm = viewModel<RequestViewModel>()
   vm.getConnections(LocalContext.current)
   LazyColumn{
      items(vm.requests.value) { user ->
         Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(10.dp),
         ) {
            ProfileAvatar(
               imageUrl = chatMessages[2].senderImageUrl,
               modifier = Modifier.size(50.dp)
            )
            Row(
               Modifier.fillMaxSize(),
               horizontalArrangement = Arrangement.SpaceBetween
            ) {
               Column(modifier = Modifier
                  .padding(6.dp), verticalArrangement = Arrangement.Center) {
                  Text(text = user.name, fontSize = 24.sp)
                  Text(text = "@"+ user.userName, fontSize = 20.sp, fontFamily = FontFamily.Monospace , fontWeight = FontWeight.Light)
               }
               Button(onClick = { /*TODO*/ }) {
                  Text(text = "accept")
               }
            }
         }
      }
   }
}