package com.sibtech.twofifty.viewmodels

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibtech.twofifty.data.apiService
import com.sibtech.twofifty.lib.getSharedPrep
import com.sibtech.twofifty.models.ConnectionResponse
import kotlinx.coroutines.launch

data class RequestsResponseModel(val id:String,  val userName:String, val email: String, val publicKey:String,val name:String)
class RequestViewModel:ViewModel() {
    var requests = mutableStateOf(listOf<RequestsResponseModel>())
    lateinit var sharedPreferences: SharedPreferences
    var isLoading = false

    fun getConnections(context: Context){
        isLoading = true
        sharedPreferences = getSharedPrep(context)
        viewModelScope.launch {
            val requestsResponse = apiService.getMyRequests("Bearer "+sharedPreferences.getString("accessToken",""))
            if(requestsResponse.isSuccessful){
                isLoading = false
                requests.value = requestsResponse.body()!!
            }else{
                isLoading = false
            }
        }
    }
}