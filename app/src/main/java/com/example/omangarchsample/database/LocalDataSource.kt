package com.example.omangarchsample.database

import com.example.omangarchsample.database.dao.UserDao
import com.example.omangarchsample.model.database.User
import com.example.omangarchsample.model.remote.UserApiModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: UserDao) {
    suspend fun insertAllUser(list: List<UserApiModel>) = dao.insert(User.toUser(list))
    suspend fun getAllUsers():List<User> =dao.getUserData()

}