package com.sibtech.twofifty.viewmodels

import android.content.Context
import android.util.Base64
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.sibtech.twofifty.data.apiService
import com.sibtech.twofifty.lib.generateKeyPair
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.lib.storeAuthRes
import com.sibtech.twofifty.models.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
class LoginViewModel : ViewModel(){
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val keys = generateKeyPair()
    val isLoading = mutableStateOf(false)
    val publicKeyString = Base64.encodeToString(keys.public.encoded,Base64.DEFAULT)
    val privateKeyString = Base64.encodeToString(keys.private.encoded,Base64.DEFAULT)
    fun login(context:Context,navHostController: NavHostController){
        isLoading.value = true
        val sharedPreferences = getSharedPrep(context)
        sharedPreferences.edit().clear().apply()
        val loginRequest = LoginRequest(email = email.value, password = password.value, publicKey = publicKeyString)
        viewModelScope.launch(Dispatchers.Default) {
            val response = apiService.login(loginRequest)
            if(response.isSuccessful){
                storeAuthRes(response.body()!!,sharedPreferences)
                sharedPreferences.edit().putString("privateKey",privateKeyString).apply()
                viewModelScope.launch(Dispatchers.Main) {
                    isLoading.value = false
                    navHostController.popBackStack()
                    navHostController.navigate("Home")
                }
            }
        }
    }
}