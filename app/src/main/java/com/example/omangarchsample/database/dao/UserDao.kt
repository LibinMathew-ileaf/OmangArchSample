package com.example.omangarchsample.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.omangarchsample.model.database.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<User>)

    @Query("SELECT * FROM user")
    suspend fun getUserData(): List<User>
}
