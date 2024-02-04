package com.example.imagesearch.presentation.mapper

import com.example.imagedatasource.domain.model.Image
import com.example.imagesearch.presentation.ImageSearchResultModel

class ImageUiMapper {

    fun mapToUI(
        domainObject: Image,
        onItemClick: (Image) -> Unit,
    ): ImageSearchResultModel = ImageSearchResultModel(
        id = domainObject.id,
        userName = domainObject.userName,
        imageUrl = domainObject.previewURL,
        tags = domainObject.tags,
        onClick = { onItemClick(domainObject) },
    )
}
