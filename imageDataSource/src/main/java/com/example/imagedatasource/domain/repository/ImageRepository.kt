package com.example.imagedatasource.domain.repository

import com.example.imagedatasource.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getImagesForQuery(query: String): Flow<List<Image>>

    suspend fun getImageById(imageId: String): Image
}
