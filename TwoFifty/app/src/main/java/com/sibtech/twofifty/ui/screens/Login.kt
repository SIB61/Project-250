package com.sibtech.twofifty.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Log In",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO */ },
            label = { Text("Email") },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO */ },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            TextButton(
                onClick = { /* TODO */
                    navHostController.navigate("register")
                },
            ) {
                Text("Register")
            }
            TextButton(
                onClick = { /* TODO */ },
            ) {
                Text("Forgot Password?")
            }
        }

        Button(
            onClick = { /* TODO */
                navHostController.popBackStack()
                navHostController.navigate("Home")
            },

            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Log In")
        }
    }
}
