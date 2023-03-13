package com.sibtech.twofifty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sibtech.twofifty.ui.components.UserListItem
import com.sibtech.twofifty.userList

@Composable
fun Connections(navHostController: NavHostController) {
   LazyColumn{
      items(userList) { user ->
         UserListItem(user = user)
      }
   }
}