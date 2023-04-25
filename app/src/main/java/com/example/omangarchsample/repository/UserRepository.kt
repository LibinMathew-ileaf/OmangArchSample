package com.example.omangarchsample.repository

import android.util.Log
import com.example.omangarchsample.NetworkResult
import com.example.omangarchsample.database.LocalDataSource
import com.example.omangarchsample.model.remote.UserApiModel
import com.example.omangarchsample.model.remote.UserResponse
import com.example.omangarchsample.remoteService.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val remoteDataSource:RemoteDataSource,private val localDataSource: LocalDataSource) {

    fun fetchUsers(page: String): Flow<NetworkResult<List<UserApiModel>>> {
        return flow {
            val userResponse: Response<UserResponse> = remoteDataSource.getUsers(page)
            if (userResponse.isSuccessful) {
                userResponse.body()?.let {
                    return@flow emit(NetworkResult.Success(it.data))
                }

            }
            return@flow emit(NetworkResult.Error(userResponse.message()))
        }.flowOn(Dispatchers.IO).catch {
                exceptions ->
            emit(NetworkResult.Failure(exceptions))
        }
    }

         suspend   fun saveUser(userList: List<UserApiModel>) =localDataSource.insertAllUser(userList)
         suspend fun getAllUsers()=localDataSource.getAllUsers()
}