package com.sibtech.twofifty.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sibtech.twofifty.ui.components.BottomNavBar
import com.sibtech.twofifty.ui.components.ChatAppTopBar
import com.sibtech.twofifty.ui.components.ChatList
import com.sibtech.twofifty.ui.navigations.HomeNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {
    val homeNavController = rememberNavController()
    Scaffold(topBar = {
        SmallTopAppBar(
            title = { Text(text = "Secure Chat") },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            actions = {

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                }
            }
        )
    },

        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(text = "New Connection")
            }
        },
        bottomBar = { BottomNavBar(homeNavController) })

    {
        Box(modifier = Modifier.padding(it)) {

            HomeNav(navHostController = homeNavController)
            
        }
    }
}