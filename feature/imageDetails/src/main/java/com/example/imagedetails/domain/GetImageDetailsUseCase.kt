package com.example.imagedetails.domain

import com.example.imagedatasource.domain.model.Image
import com.example.imagedatasource.domain.repository.ImageRepository
import javax.inject.Inject

class GetImageDetailsUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    suspend fun execute(imageId: String): Image {
        return imageRepository.getImageById(imageId)
    }
}
