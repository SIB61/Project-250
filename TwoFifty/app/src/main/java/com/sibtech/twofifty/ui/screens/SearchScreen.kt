package com.sibtech.twofifty.ui.screens
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sibtech.twofifty.chatMessages
import com.sibtech.twofifty.ui.components.ProfileAvatar
import com.sibtech.twofifty.viewmodels.SearchViewModel

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navHostController: NavHostController) {

    val vm = viewModel<SearchViewModel>()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    TextField(value = vm.searchText.value,
                        placeholder = { Text(text = "Search by username") },
                        onValueChange = { vm.searchText.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent
                        ),
                        trailingIcon = {
                            IconButton(onClick = {
                                vm.search(context)
                            }) {
                                Icon(imageVector = Icons.Default.Search, contentDescription = null)
                            }
                        }
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
            )
        },
    ) {
       Box(modifier = Modifier
           .padding(it)
           .fillMaxSize()){
           if(vm.user.value.id!="")
           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(16.dp),
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               ProfileAvatar(imageUrl = chatMessages[2].senderImageUrl, modifier = Modifier.size(200.dp))
               Spacer(modifier = Modifier.height(20.dp))
               Text(text = vm.user.value.name, fontSize = 30.sp)
               Text(text = vm.user.value.email, fontSize = 24.sp)
               Text(text = "@"+vm.user.value.userName, fontSize = 20.sp )

               if(vm.getActionText().isNotEmpty())
                   Button(onClick = {
                   vm.updateConnection(context)
               }) {
                   Text(text = vm.getActionText())
               }
               else Text("requested",Modifier.alpha(0.7f))
           }
       }
    }
}