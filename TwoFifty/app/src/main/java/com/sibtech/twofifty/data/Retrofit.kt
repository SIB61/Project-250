package com.sibtech.twofifty.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sibtech.twofifty.models.AuthResponse
import com.sibtech.twofifty.models.LoginRequest
import com.sibtech.twofifty.models.RegistrationRequest
import com.sibtech.twofifty.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

val retrofit = Retrofit.Builder()
    .baseUrl("http://192.168.0.111:3000/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()


interface MyApi {

    @POST("user/login")
    suspend fun login(@Body request:LoginRequest): Response<AuthResponse>

    @POST("user/registration")
    suspend fun registration(@Body request: RegistrationRequest): Response<AuthResponse>

}

val apiService = retrofit.create(MyApi::class.java)
