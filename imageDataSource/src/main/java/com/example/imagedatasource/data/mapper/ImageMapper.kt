package com.example.imagedatasource.data.mapper

import com.example.imagedatasource.data.db.ImageEntity
import com.example.imagedatasource.data.db.SearchResultEntity
import com.example.imagedatasource.data.remote.model.ImageDto
import com.example.imagedatasource.domain.model.Image
import javax.inject.Inject

internal class ImageMapper @Inject constructor() {

    fun mapDtoToDomain(dto: ImageDto): Image = Image(
        id = dto.id.toString(),
        userName = dto.user,
        previewURL = dto.previewURL,
        largeImageURL = dto.largeImageURL,
        tags = dto.tags.split(", "),
        likesCount = dto.likes,
        downloadsCount = dto.downloads,
        commentsCount = dto.comments,
    )

    fun mapEntityToDomain(entity: ImageEntity): Image = Image(
        id = entity.id,
        userName = entity.userName,
        previewURL = entity.previewURL,
        largeImageURL = entity.largeImageURL,
        tags = entity.tags.split(", "),
        likesCount = entity.likesCount,
        downloadsCount = entity.downloadsCount,
        commentsCount = entity.commentsCount,
    )

    fun mapDomainToEntity(domainObject: Image): ImageEntity = ImageEntity(
        id = domainObject.id,
        userName = domainObject.userName,
        previewURL = domainObject.previewURL,
        largeImageURL = domainObject.largeImageURL,
        tags = domainObject.tags.joinToString(),
        likesCount = domainObject.likesCount,
        downloadsCount = domainObject.downloadsCount,
        commentsCount = domainObject.commentsCount,
    )

    fun mapToSearchResult(
        query: String,
        images: List<ImageEntity>
    ): SearchResultEntity = SearchResultEntity(
        query = query,
        ids = images.map { it.id }
    )
}
