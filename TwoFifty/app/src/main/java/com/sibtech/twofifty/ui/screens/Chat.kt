package com.sibtech.twofifty.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sibtech.twofifty.chatMessages
import com.sibtech.twofifty.data.apiService
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.ui.components.LoadingModal
import com.sibtech.twofifty.ui.components.ProfileAvatar
import com.sibtech.twofifty.viewmodels.ChatScreenViewModel
import java.security.PublicKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navHostController: NavHostController,username:String,name:String,id:String) {
    val vm = viewModel<ChatScreenViewModel>()
    val context = LocalContext.current
    var publicKey by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = id){
        vm.isLoading.value = true
        apiService.connectMessage("Bearer "+ getSharedPrep(context).getString("accessToken","")!!,id)
        val res= apiService.searchUser("Bearer "+ getSharedPrep(context).getString("accessToken","")!!,username)
        if(res.isSuccessful){
            vm.isLoading.value= false
            publicKey = res.body()?.publicKey ?: ""
        }
        vm.start(context)
    }

    if(vm.isLoading.value){
        LoadingModal()
    }
    BoxWithConstraints {
        Scaffold(topBar = {
            SmallTopAppBar(
                title = {
                    Row {
                        ProfileAvatar(imageUrl = chatMessages[2].senderImageUrl)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = name)
                            Text(text = "@"+username)
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
            )
        },
            bottomBar = {
                BottomAppBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
                    TextField(value =vm.messageText.value , onValueChange ={vm.messageText.value=it},  colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    ),
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "message")},
                        trailingIcon = {
                            IconButton(onClick = {
                                vm.sendMessage(context = context, receiverId = id, receiverPublicKey = publicKey)
                            }) {
                                Icon(imageVector =Icons.Default.Send , contentDescription = null )
                            }
                        }
                    )
                }
            }
        ) {
            BoxWithConstraints(Modifier.padding(it)) {
                LazyColumn{
                   items(vm.messageList){
                       Text(text = it.encryptedMsg)
                   }
                }

            }
        }
    }

}