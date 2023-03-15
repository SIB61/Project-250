package com.sibtech.twofifty.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sibtech.dinlipi.presentation.views.screens.SplashScreen
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.ui.screens.*

@Composable
fun MainNav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable(route = "splash") {
            SplashScreen(navController = navController);
        }
        composable(route = "Home") {
            Home(navController = navController)
        }
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "register") {
            RegistrationScreen(navHostController = navController)
        }
        composable(route = "search") {
            SearchScreen(navHostController = navController)
        }
        composable(route = "chat/{username}/{name}/{id}") {
            val username = it.arguments?.getString("username")!!
            val id = it.arguments?.getString("id")!!
            val name = it.arguments?.getString("name")!!
            ChatScreen(navHostController = navController, username = username, name = name, id = id)
        }
    }
}