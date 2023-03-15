package com.sibtech.twofifty.viewmodels
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibtech.twofifty.data.apiService
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.lib.storeAuthRes
import com.sibtech.twofifty.models.LoginRequest
import com.sibtech.twofifty.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchResponse(val connectionStatus:String=""):UserModel()

class SearchViewModel:ViewModel() {
    val user = mutableStateOf<SearchResponse>(SearchResponse())
    val searchText = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    fun search(context:Context){
        isLoading.value = true
        val sharedPreferences = getSharedPrep(context)
        viewModelScope.launch {
            val requestsResponse = apiService.searchUser("Bearer "+sharedPreferences.getString("accessToken",""),searchText.value)
            if(requestsResponse.isSuccessful){
                isLoading.value = false
                user.value = requestsResponse.body()!!
            }else{
                isLoading.value = false
            }
        }
    }

    fun getActionText():String{
       when(user.value.connectionStatus){
          "connected"->return "chat"
           "disconnected"->return "request"
           "requested"->return "abort"
           "pending"->return "request"
           else -> return "request"
       }
    }
}