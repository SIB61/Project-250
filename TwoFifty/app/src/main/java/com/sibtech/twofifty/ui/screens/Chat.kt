package com.sibtech.twofifty.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import com.sibtech.twofifty.ui.components.ProfileAvatar
import com.sibtech.twofifty.viewmodels.ChatScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navHostController: NavHostController,username:String,name:String,id:String) {
    val vm = viewModel<ChatScreenViewModel>()
    val context = LocalContext.current
    LaunchedEffect(key1 = id){
        apiService.connectMessage("Bearer "+ getSharedPrep(context).getString("accessToken","")!!,id)
        vm.start()
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
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector =Icons.Default.Send , contentDescription = null )
                            }
                        }
                    )
                }
            }
        ) {
            BoxWithConstraints(Modifier.padding(it)) {
                Text(text = name)

            }
        }
    }

}