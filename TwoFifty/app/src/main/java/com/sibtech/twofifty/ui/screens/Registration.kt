package com.sibtech.twofifty.ui.screens

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
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.models.RegistrationRequest
import com.sibtech.twofifty.ui.components.LoadingModal
import com.sibtech.twofifty.viewmodels.RegistrationViewModel

@Composable
fun RegistrationScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val sharedPrep = getSharedPrep(context)
    val vm = viewModel<RegistrationViewModel>()

    if(vm.isLoading.value)
        LoadingModal()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create an Account",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = vm.name.value,
            onValueChange = { vm.name.value = it },
            label = { Text(text = "name") },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.userName.value,
            onValueChange = { vm.userName.value = it },
            label = { Text("user name") },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
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

        Button(
            onClick = { /* TODO */
                vm.register(context,navHostController)
            },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}
