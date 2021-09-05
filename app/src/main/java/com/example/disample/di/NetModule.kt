package com.example.disample.di

import android.content.Context
import com.example.disample.BuildConfig
import com.example.disample.data.localdata.UserApiServiceMockResponse
import com.example.disample.network.api.ApiService
import com.example.disample.network.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dependency injection class that provides instances related to network utils / api calls
 */
@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMockUserService(): UserApiServiceMockResponse {
        return UserApiServiceMockResponse()
    }

    @Provides
    @Singleton
    fun networkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }

}