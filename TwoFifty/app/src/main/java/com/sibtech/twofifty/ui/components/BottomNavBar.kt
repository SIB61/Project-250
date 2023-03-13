package com.sibtech.twofifty.ui.components

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions


@Composable
fun BottomNavBar(navHostController: NavHostController) {

    val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current
    NavigationBar {
        NavigationBarItem(
            selected = navHostController.currentDestination?.route == "chats",
            onClick = {
                if(navHostController.currentDestination?.route!="chats"){
                    backPressDispatcher?.onBackPressedDispatcher?.onBackPressed()
                }
            },
            label = { Text(text = "Chats") },
            icon = {
                Icon(
                    painter = painterResource(id = com.sibtech.twofifty.R.drawable.ic_chat),
                    contentDescription = null
                )
            })

        NavigationBarItem(

            selected = navHostController.currentDestination?.route == "connections",
            onClick = {
                if(navHostController.currentDestination?.route!="connections")
                {
                    navHostController.navigate("connections"){
                        this.popUpTo("chats")
                    }
                }
            },
            label = { Text(text = "Connections") },
            icon = {
                Icon(
                    painter = painterResource(id = com.sibtech.twofifty.R.drawable.ic_people),
                    contentDescription = null
                )
            })

        NavigationBarItem(
            selected = navHostController.currentDestination?.route == "profile",
            onClick = {
                if(navHostController.currentDestination?.route != "profile"){
                    navHostController.navigate("profile"){
                        this.popUpTo("chats")
                    }
                }
            },
            label = {
                Text(text = "Profile")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            })
    }
}