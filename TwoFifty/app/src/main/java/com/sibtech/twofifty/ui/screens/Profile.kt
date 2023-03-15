package com.sibtech.twofifty.ui.screens

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sibtech.twofifty.chatMessages
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.models.User
import com.sibtech.twofifty.ui.components.ProfileAvatar
import com.sibtech.twofifty.userList

@Composable
fun Profile(navHostController: NavHostController, parentNavigate:(route:String)->Unit) {
    val sharedPreferences= getSharedPrep(LocalContext.current)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileAvatar(imageUrl = chatMessages[2].senderImageUrl, modifier = Modifier.size(200.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = sharedPreferences.getString("name","")!!, fontSize = 30.sp)
        Text(text = sharedPreferences.getString("email","")!!, fontSize = 24.sp)
        Text(text = "@"+sharedPreferences.getString("userName","")!!, fontSize = 20.sp )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "edit")
        }
    }
}

