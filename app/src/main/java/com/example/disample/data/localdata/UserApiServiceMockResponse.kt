package com.example.disample.data.localdata

import com.example.disample.data.model.APIResponse
import com.example.disample.data.model.User
import retrofit2.Response

/**
 * Mock user login/register that supposes the api call is successful
 */

class UserApiServiceMockResponse {
    fun loginUser(user: User): APIResponse<User> {  // Success
        return APIResponse(user, "success", null)
    }

    fun registerUser(user: User): APIResponse<User> {  // Success
        return APIResponse(user, "success", null)
    }
}