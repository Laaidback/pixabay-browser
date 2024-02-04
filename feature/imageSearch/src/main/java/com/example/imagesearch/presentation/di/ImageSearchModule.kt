package com.example.imagesearch.presentation.di

import com.example.core.navigation.NavigationFactory
import com.example.imagesearch.presentation.ImageSearchNavigationFactory
import com.example.imagesearch.presentation.mapper.ImageUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ImageSearchModule {

    @Provides
    @IntoSet
    @Singleton
    fun provideImageSearchNavigationFactory(): NavigationFactory = ImageSearchNavigationFactory()

    @Provides
    @Singleton
    fun provideImageUiMapper(): ImageUiMapper = ImageUiMapper()
}
