package com.example.omangarchsample.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.omangarchsample.model.remote.UserApiModel

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var firstName: String,
    var email: String,
    var lastName: String,
    var avatar: String
) {
    companion object {
        fun toUser(userApiModels: List<UserApiModel>): List<User> {
            return userApiModels.map {
                User(
                    id = it.id, firstName = it.firstName ?: "",
                    email = it.email ?: "", lastName =
                    it.lastName ?: "", avatar = it.avatar ?: ""
                )
            }

        }
    }
}



