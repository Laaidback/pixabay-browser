package com.example.imagesearch.domain

import com.example.core.utils.asResult
import com.example.imagedatasource.domain.model.Image
import com.example.imagedatasource.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SearchImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    suspend fun execute(query: String): Flow<Result<List<Image>>> {
        return imageRepository.getImagesForQuery(query)
            .asResult()
    }
}
