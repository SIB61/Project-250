package com.sibtech.twofifty.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sibtech.twofifty.ui.screens.Connections
import com.sibtech.twofifty.ui.screens.Profile
import com.sibtech.twofifty.ui.screens.Requests

@Composable
fun HomeNav(navHostController: NavHostController,parentNavigate:(route:String)->Unit) {
   NavHost(navController = navHostController, startDestination = "connections"){
       composable(route = "connections"){
           Connections(navHostController,parentNavigate)
       }
       composable(route = "requests"){
           Requests(navHostController,parentNavigate)
       }
       composable(route = "profile"){
           Profile(navHostController,parentNavigate)
       }
   }
}