package com.example.imagedetails.di

import com.example.core.navigation.NavigationFactory
import com.example.imagedetails.presentation.ImageDetailsNavigationFactory
import com.example.imagedetails.presentation.mapper.ImageDetailsUiModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ImageDetailsModule {

    @Provides
    @IntoSet
    @Singleton
    fun provideImageSearchNavigationFactory(): NavigationFactory = ImageDetailsNavigationFactory()

    @Provides
    @Singleton
    fun provideImageDetailsUiModelMapper(): ImageDetailsUiModelMapper = ImageDetailsUiModelMapper()
}
