package com.sibtech.twofifty.ui.screens
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        SmallTopAppBar(
            title = { TextField(value = "", onValueChange = {}, modifier = Modifier.focusable(true), colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            )) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            actions = {
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        )
    },
    ) {
       Box(modifier = Modifier
           .padding(it)
           .fillMaxSize()){

       }
    }
}