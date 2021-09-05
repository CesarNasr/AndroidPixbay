package com.example.disample.di

import com.example.disample.data.localdata.UserApiServiceMockResponse
import com.example.disample.data.repository.HomeRepository
import com.example.disample.data.repository.LoginRepository
import com.example.disample.data.repositoryImpl.LoginRepositoryImpl
import com.example.disample.data.repository.RegisterRepository
import com.example.disample.data.repositoryImpl.HomeRepositoryImpl
import com.example.disample.data.repositoryImpl.RegisterRepositoryImpl
import com.example.disample.network.api.ApiService
import com.example.disample.network.utils.ResponseConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency injection class that provides instances related to repositories
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideResponseConverter(): ResponseConverter {
        return ResponseConverter()
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        userApiServiceMockResponse: UserApiServiceMockResponse,
        responseConverter: ResponseConverter
    ): LoginRepository {
        return LoginRepositoryImpl(userApiServiceMockResponse, responseConverter)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(
        userApiServiceMockResponse: UserApiServiceMockResponse,
        responseConverter: ResponseConverter
    ): RegisterRepository {
        return RegisterRepositoryImpl(userApiServiceMockResponse, responseConverter)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        apiService: ApiService,
        responseConverter: ResponseConverter
    ): HomeRepository {
        return HomeRepositoryImpl(apiService, responseConverter)
    }


}