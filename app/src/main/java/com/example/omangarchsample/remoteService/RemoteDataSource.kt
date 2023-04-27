package com.example.omangarchsample.remoteService

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val omangAPIService: OmangAPIService) {
    suspend fun getUsers(page:String) = omangAPIService.getUsers(page)
}