package com.sibtech.twofifty.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sibtech.twofifty.ui.screens.Chats
import com.sibtech.twofifty.ui.screens.Connections
import com.sibtech.twofifty.ui.screens.Profile

@Composable
fun HomeNav(navHostController: NavHostController) {
   NavHost(navController = navHostController, startDestination = "chats"){
       composable(route = "chats"){
           Chats(navHostController)
       }
       composable(route = "connections"){
           Connections(navHostController)
       }
       composable(route = "profile"){
           Profile(navHostController)
       }
   }
}