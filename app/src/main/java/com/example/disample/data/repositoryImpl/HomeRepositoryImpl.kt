package com.example.disample.data.repositoryImpl

import com.example.disample.BuildConfig
import com.example.disample.data.localdata.UserApiServiceMockResponse
import com.example.disample.data.model.image.PixbayEntryPoint
import com.example.disample.data.repository.HomeRepository
import com.example.disample.data.repository.LoginRepository
import com.example.disample.network.api.ApiService
import com.example.disample.network.utils.Resource
import com.example.disample.network.utils.ResponseConverter
import javax.inject.Inject
/**
 *  Actual implementation of the repository that communicates with remote source (in our case) and to local database if we had one
 *  Repository acts as a single source of truth for data in our app
 */
class HomeRepositoryImpl
@Inject constructor(
    private val apiService: ApiService,
    private val responseConverter: ResponseConverter
) : HomeRepository {
    override suspend fun fetchImages(): Resource<PixbayEntryPoint> {
        return responseConverter.onlineResponseToResult(apiService.getImages(BuildConfig.API_KEY))
    }
}