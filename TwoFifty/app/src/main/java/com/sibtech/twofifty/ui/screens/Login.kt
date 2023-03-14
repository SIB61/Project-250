package com.sibtech.twofifty.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sibtech.twofifty.data.apiService
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.lib.storeAuthRes
import com.sibtech.twofifty.models.LoginRequest
import com.sibtech.twofifty.ui.components.LoadingModal
import com.sibtech.twofifty.viewmodels.LoginViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val sharedPref = getSharedPrep(context)
    val vm = viewModel<LoginViewModel>()

   if(vm.isLoading.value){
       LoadingModal()
   }
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
            value = vm.email.value,
            onValueChange = { vm.email.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = vm.password.value,
            onValueChange = { vm.password.value = it },
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
                vm.login(context,navHostController)
            },

            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Log In")
        }
    }
}
