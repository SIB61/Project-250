package com.sibtech.twofifty.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibtech.twofifty.data.apiService
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.models.ConnectionResponse
import com.sibtech.twofifty.models.UserModel
import kotlinx.coroutines.launch

class ConnectionsViewModel : ViewModel(){
    var connections = mutableStateOf(listOf<UserModel>())
    lateinit var sharedPreferences: SharedPreferences
    var isLoading = false

    fun getConnections(context: Context){
        isLoading = true
       sharedPreferences = getSharedPrep(context)
       viewModelScope.launch {
          val connectionsResponse = apiService.getMyConnections("Bearer "+sharedPreferences.getString("accessToken",""))
           if(connectionsResponse.isSuccessful){
             isLoading = false
             connections.value = connectionsResponse.body()!!
           }else{
              isLoading = false
           }
       }
    }
}