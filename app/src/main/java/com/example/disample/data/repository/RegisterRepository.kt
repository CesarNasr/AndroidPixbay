package com.example.disample.data.repository

import com.example.disample.data.model.APIResponse
import com.example.disample.data.model.User
import com.example.disample.network.utils.Resource
/**
 * Repository interface for the purpose of abstraction between data layer and another presentation layer
 */
interface RegisterRepository {
    suspend fun registerUser(user: User): Resource<APIResponse<User>>
}