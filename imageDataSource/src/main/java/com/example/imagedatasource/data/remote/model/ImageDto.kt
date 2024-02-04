package com.example.imagedatasource.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal  data class ImageDto(
    @SerialName("comments") val comments: Int,
    @SerialName("downloads") val downloads: Int,
    @SerialName("fullHDURL") val fullHDURL: String? = null,
    @SerialName("id") val id: Long,
    @SerialName("imageHeight") val imageHeight: Int,
    @SerialName("imageSize") val imageSize: Int,
    @SerialName("imageURL") val imageURL: String? = null,
    @SerialName("imageWidth") val imageWidth: Int,
    @SerialName("largeImageURL") val largeImageURL: String,
    @SerialName("likes") val likes: Int,
    @SerialName("pageURL") val pageURL: String,
    @SerialName("previewHeight") val previewHeight: Int,
    @SerialName("previewURL") val previewURL: String,
    @SerialName("previewWidth") val previewWidth: Int,
    @SerialName("tags") val tags: String,
    @SerialName("type") val type: String,
    @SerialName("user") val user: String,
    @SerialName("userImageURL") val userImageURL: String,
    @SerialName("user_id") val userId: Int,
    @SerialName("views") val views: Int,
    @SerialName("webformatHeight") val webformatHeight: Int,
    @SerialName("webformatURL") val webformatURL: String,
    @SerialName("webformatWidth") val webformatWidth: Int
)
