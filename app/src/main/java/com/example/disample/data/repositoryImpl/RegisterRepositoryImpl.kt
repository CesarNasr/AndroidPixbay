package com.example.disample.data.repositoryImpl

import com.example.disample.data.localdata.UserApiServiceMockResponse
import com.example.disample.data.model.APIResponse
import com.example.disample.data.model.User
import com.example.disample.data.repository.RegisterRepository
import com.example.disample.network.utils.Resource
import com.example.disample.network.utils.ResponseConverter
import javax.inject.Inject
/**
 *  Actual implementation of the repository that communicates with remote source (in our case) and to local database if we had one
 *  Repository acts as a single source of truth for data in our app
 */
class RegisterRepositoryImpl @Inject constructor(
    private val userApiServiceMockResponse: UserApiServiceMockResponse,
    private val responseConverter: ResponseConverter
) :
    RegisterRepository {
    override suspend fun registerUser(user: User): Resource<APIResponse<User>> {
        return responseConverter.localResponseToResult(userApiServiceMockResponse.registerUser(user))
    }

}