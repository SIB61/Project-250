package com.sibtech.twofifty.data

import com.google.gson.JsonObject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sibtech.twofifty.models.*
import com.sibtech.twofifty.viewmodels.RequestsResponseModel
import com.sibtech.twofifty.viewmodels.SearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Objects

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

    @GET("connection")
    suspend fun getMyConnections(@Header("authorization") header: String): Response<List<UserModel>>

    @GET("connection/requests")
    suspend fun getMyRequests(@Header("authorization") header: String): Response<List<RequestsResponseModel>>


    @GET("user/{username}")
    suspend fun searchUser(@Header("authorization") header: String,@Path("username") userName:String): Response<SearchResponse>

    @GET("message")
    suspend fun connectMessage(@Header("authorization") header: String, @Query("withUserId") id:String): Response<ConnectionResponse>

    @POST("connection")
    suspend fun updateConnectionStatus(@Header("authorization") header: String, @Query("withUserId") id:String, @Body status:ConnectionStatus): Response<ConnectionStatus>
}

data class ConnectionStatus(val status:String="")
val apiService = retrofit.create(MyApi::class.java)
