package com.example.imagedatasource.data.di

import com.example.imagedatasource.data.db.ImageDao
import com.example.imagedatasource.data.mapper.ImageMapper
import com.example.imagedatasource.data.remote.service.ImageService
import com.example.imagedatasource.data.repository.ImageRepositoryImpl
import com.example.imagedatasource.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ImageDataModule {

    @Provides
    @Singleton
    fun provideImageService(
        retrofit: Retrofit,
    ): ImageService = retrofit.create(ImageService::class.java)

    @Provides
    @Singleton
    fun provideImageRepository(
        imageService: ImageService,
        imageDao: ImageDao,
        imageMapper: ImageMapper,
    ): ImageRepository = ImageRepositoryImpl(
        imageService = imageService,
        imageDao = imageDao,
        imageMapper = imageMapper
    )
}
