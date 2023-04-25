package com.example.omangarchsample.remoteService

import com.example.omangarchsample.model.remote.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OmangAPIService {
    @GET("users")
    suspend fun getUsers(@Query("page")  pageNo:String): Response<UserResponse>
}