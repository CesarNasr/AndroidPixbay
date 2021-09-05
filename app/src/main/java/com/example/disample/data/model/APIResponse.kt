package com.example.disample.data.model

import com.google.gson.annotations.SerializedName

/**
 * In the case of a normal API, this can be used for user operations as well as for pixbay api responses
 */

data class APIResponse<T>(

    @SerializedName("data")
    val data: T,  // Can be dynamic, can be static, depends on Api response structure

    @SerializedName("status")
    val status: String,
    @SerializedName("errorCode")
    val errorCode: Int? = null,
    @SerializedName("errorMessage")
    val errorMessage: String? = null,

    )
