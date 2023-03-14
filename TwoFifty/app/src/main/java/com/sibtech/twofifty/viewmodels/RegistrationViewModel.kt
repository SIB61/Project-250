package com.sibtech.twofifty.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.sibtech.twofifty.data.apiService
import com.sibtech.twofifty.lib.generateKeyPair
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.lib.storeAuthRes
import com.sibtech.twofifty.models.RegistrationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Base64
import kotlin.coroutines.coroutineContext

class RegistrationViewModel : ViewModel() {
    var name = mutableStateOf("")
    var email = mutableStateOf("")
    var userName = mutableStateOf("")
    var password = mutableStateOf("")
    val keys = generateKeyPair()
    val isLoading = mutableStateOf(false)
    val publicKeyString = android.util.Base64.encodeToString(keys.public.encoded,
        android.util.Base64.DEFAULT)
    val privateKeyString = android.util.Base64.encodeToString(keys.private.encoded,
        android.util.Base64.DEFAULT)


    fun register(context: Context, navHostController: NavHostController) {
        isLoading.value = true
        val registrationRequest = RegistrationRequest(
            userName = userName.value,
            password = password.value,
            email = email.value,
            name = name.value,
            publicKey = publicKeyString
        )
        val sharedPref = getSharedPrep(context)
        sharedPref.edit().clear().apply()
        viewModelScope.launch(Dispatchers.Default) {
            val registrationResponse = apiService.registration(registrationRequest)
            if (registrationResponse.isSuccessful) {
                val body = registrationResponse.body()
                storeAuthRes(body!!, sharedPref)
                sharedPref.edit().putString(
                    "privateKey",
                    privateKeyString
                ).apply()
                viewModelScope.launch(Dispatchers.Main) {
                    isLoading.value = false
                    navHostController.popBackStack()
                    navHostController.navigate("Home")
                }
            }
        }
    }
}