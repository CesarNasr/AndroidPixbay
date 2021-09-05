package com.example.disample.data.model.image


import com.example.disample.data.model.ErrorResponse
import com.google.gson.annotations.SerializedName

data class PixbayEntryPoint(
    @SerializedName("hits")
    val hits: List<Hit>? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("totalHits")
    val totalHits: Int? = null,
    val errorResponse: ErrorResponse? =null
)