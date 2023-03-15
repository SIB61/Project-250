package com.sibtech.twofifty.viewmodels
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibtech.twofifty.data.ConnectionStatus
import com.sibtech.twofifty.data.apiService
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.lib.storeAuthRes
import com.sibtech.twofifty.models.ConnectionResponse
import com.sibtech.twofifty.models.LoginRequest
import com.sibtech.twofifty.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchResponse(var connectionStatus:String=""):UserModel()

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
           "requested"->return "accept"
           else -> return ""
       }
    }


    fun getRequestStatus():String{
        when(user.value.connectionStatus){
            "disconnected"->return "pending"
            "requested","pending"->return "connected"
            else -> return "disconnected"
        }
    }

    fun updateConnection(ctx:Context){
        isLoading.value = true
       viewModelScope.launch {
           val token = getSharedPrep(ctx).getString("accessToken","")!!
           val statusRes = apiService.updateConnectionStatus("Bearer "+token, id = user.value.id, status = ConnectionStatus(status = getRequestStatus()))
           if(statusRes.isSuccessful){
               isLoading.value = false
               user.value.connectionStatus = statusRes.body()?.status ?: user.value.connectionStatus
           }
       }
    }
}