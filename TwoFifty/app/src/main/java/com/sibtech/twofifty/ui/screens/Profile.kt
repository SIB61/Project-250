package com.sibtech.twofifty.ui.screens

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sibtech.twofifty.models.User
import com.sibtech.twofifty.ui.components.ProfileAvatar
import com.sibtech.twofifty.userList

@Composable
fun Profile(navHostController: NavHostController) {
    val user = userList[1]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileAvatar(imageUrl = user.profileImageUrl, modifier = Modifier.size(200.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Md Sabit Islam Bhuiya", fontSize = 30.sp)
        Text(text = "sib.sustswe@gmail.com", fontSize = 24.sp)
        Text(text = "total connections: 30", fontSize = 20.sp )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "edit")
        }
    }
}

