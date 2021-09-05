package com.example.disample.data.repository

import com.example.disample.data.model.image.PixbayEntryPoint
import com.example.disample.network.utils.Resource

/**
 * Repository interface for the purpose of abstraction between data layer and another presentation layer
 */

interface HomeRepository {
    suspend fun fetchImages(): Resource<PixbayEntryPoint>
}