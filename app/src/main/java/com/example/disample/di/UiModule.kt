package com.example.disample.di

import com.example.disample.ui.adapters.ImageListAdapter
import com.example.disample.utils.Validators
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * Dependency injection class that provides instances related to UI utils
 */

@Module
@InstallIn(SingletonComponent::class)
object UiModule {
    @Provides
    @Singleton
    fun provideImageListAdapter(): ImageListAdapter {
        return ImageListAdapter()
    }

    @Provides
    @Singleton
    fun provideValidators(): Validators {
        return Validators()
    }
}