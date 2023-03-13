package com.sibtech.twofifty.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sibtech.dinlipi.presentation.views.screens.SplashScreen
import com.sibtech.twofifty.ui.screens.Home
import com.sibtech.twofifty.ui.screens.LoginScreen
import com.sibtech.twofifty.ui.screens.RegistrationScreen

@Composable
fun MainNav(navController: NavHostController) {
  NavHost(navController = navController, startDestination = "splash"){
      composable(route = "splash"){
         SplashScreen(navController = navController);
      }
      composable(route = "Home"){
          Home(navController=navController)
      }
      composable(route = "login"){
          LoginScreen(navController)
      }
      composable(route = "register"){
          RegistrationScreen(navHostController = navController)
      }
  }
}