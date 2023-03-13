package com.sibtech.twofifty.data

import com.sibtech.twofifty.models.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface MyApi {
    @GET("users")
    suspend fun getUsers(): List<User>



}
