package com.example.disample.network.utils

import com.example.disample.data.model.APIResponse
import com.example.disample.data.model.ErrorResponse
import com.example.disample.data.model.User
import com.example.disample.data.model.image.PixbayEntryPoint
import com.google.gson.Gson
import retrofit2.Response
/**
 * Responsible for converting api response to resource class
 * We can use only "onlineResponseToResult()" fun in case we had the same EntryAPiResponse for all endpoints
 */
class ResponseConverter {


    fun localResponseToResult(response: APIResponse<User>): Resource<APIResponse<User>> {
        if (response.status == "success") {
            return Resource.Success(response)
        }
        return response.errorMessage?.let { Resource.Error(it) }!!
    }

    fun onlineResponseToResult(response: Response<PixbayEntryPoint>): Resource<PixbayEntryPoint> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        response.errorBody()?.let {
            return Resource.Error(
                it.string(),
            )
        }
        return Resource.Error(response.message())
    }

}