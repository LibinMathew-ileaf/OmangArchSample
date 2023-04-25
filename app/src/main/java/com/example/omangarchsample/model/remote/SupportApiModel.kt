package com.example.omangarchsample.model.remote

import com.google.gson.annotations.SerializedName


data class SupportApiModel(
    @SerializedName("url") var url: String? = null,
    @SerializedName("text") var text: String? = null
)