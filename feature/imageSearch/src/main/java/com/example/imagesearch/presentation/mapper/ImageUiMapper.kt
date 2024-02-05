package com.example.imagesearch.presentation.mapper

import com.example.core.presentation.model.ChipModel
import com.example.imagedatasource.domain.model.Image
import com.example.imagesearch.presentation.ImageSearchResultModel

class ImageUiMapper {

    fun mapToUI(
        domainObject: Image,
        onItemClick: (Image) -> Unit,
        onChipClick: (String) -> Unit,
    ): ImageSearchResultModel = ImageSearchResultModel(
        id = domainObject.id,
        userName = domainObject.userName,
        imageUrl = domainObject.previewURL,
        tags = domainObject.tags.map { tag ->
            ChipModel(
                title = tag,
                onClick = { onChipClick(tag) }
            )
        },
        onClick = { onItemClick(domainObject) },
    )
}
