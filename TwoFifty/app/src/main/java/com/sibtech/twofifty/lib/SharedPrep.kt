package com.sibtech.twofifty.lib

import android.content.Context
import android.content.SharedPreferences
import com.sibtech.twofifty.models.AuthResponse

fun getSharedPrep(context: Context): SharedPreferences {
    return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
}

fun storeAuthRes(authResponse: AuthResponse,sharedPreferences: SharedPreferences){
    val editor = sharedPreferences.edit()
    editor.putString("accessToken",authResponse.accessToken)
    editor.putString("userName",authResponse.userName)
    editor.putString("id",authResponse.id)
    editor.putString("email",authResponse.email)
    editor.putString("name",authResponse.name)
    editor.apply()
}