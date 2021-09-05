package com.example.disample.data.model

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("header")
    val header: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("name")
    val name: String?  ,

    @SerializedName("code")
    val code: String?
)