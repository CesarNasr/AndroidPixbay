package com.example.disample.network.api

import com.bumptech.glide.load.engine.Resource
import com.example.disample.data.model.image.PixbayEntryPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api Service class calling @GET , @POST,  @DELETE, @UPDATE, @EDIT etc ...
 */
interface ApiService {
    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String
    ): Response<PixbayEntryPoint>
}