package com.example.imagedetails.presentation.mapper

import com.example.core.presentation.model.ChipModel
import com.example.imagedatasource.domain.model.Image
import com.example.imagedetails.presentation.ImageDetails

class ImageDetailsUiModelMapper {

    fun mapToUI(domainObject: Image): ImageDetails {
        return ImageDetails(
            userName = domainObject.userName,
            imageUrl = domainObject.largeImageURL,
            tags = domainObject.tags.map {
                ChipModel(title = it, onClick = {})
            },
            likesCount = domainObject.likesCount,
            downloadsCount = domainObject.downloadsCount,
            commentsCount = domainObject.commentsCount,
        )
    }
}
