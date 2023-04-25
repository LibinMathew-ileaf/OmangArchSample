package com.example.omangarchsample.model.remote

import com.google.gson.annotations.SerializedName


data class UserApiModel(
    @SerializedName("id") var id: Int ,
    @SerializedName("email") var email: String? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("avatar") var avatar: String? = null

)