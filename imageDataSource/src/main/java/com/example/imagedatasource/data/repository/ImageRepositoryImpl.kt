package com.example.imagedatasource.data.repository

import com.example.core.utils.urlEncoded
import com.example.imagedatasource.data.db.ImageDao
import com.example.imagedatasource.data.mapper.ImageMapper
import com.example.imagedatasource.data.remote.service.ImageService
import com.example.imagedatasource.domain.model.Image
import com.example.imagedatasource.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException

internal class ImageRepositoryImpl(
    private val imageService: ImageService,
    private val imageDao: ImageDao,
    private val imageMapper: ImageMapper,
) : ImageRepository {

    override suspend fun getImagesForQuery(query: String): Flow<List<Image>> = flow {
        try {
            val response = imageService.getImages(query = query.urlEncoded)
            val images = response.images.map(imageMapper::mapDtoToDomain)
            savePersistently(images, query)
            emit(images)
        } catch (exception: IOException) {
            Timber.e(exception)
            val searchResult = imageDao.getSearchResultsFromQuery(query = query) ?: throw exception
            val images = searchResult.ids
                .map { id -> imageDao.getImageById(id) }
                .map(imageMapper::mapEntityToDomain)
            emit(images)
        }
    }

    override suspend fun getImageById(imageId: String): Image {
        return imageDao.getImageById(imageId = imageId).let(imageMapper::mapEntityToDomain)
    }

    private suspend fun savePersistently(
        images: List<Image>,
        query: String
    ) {
        val entitiesToSave = images.map(imageMapper::mapDomainToEntity)
        imageDao.saveImages(entitiesToSave)
        val searchResult = imageMapper.mapToSearchResult(query, entitiesToSave)
        imageDao.saveSearchResult(searchResult)
    }
}
