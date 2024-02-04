package com.example.imagedatasource.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchImageResponse(
    @SerialName("hits") val images: List<ImageDto>,
    @SerialName("total") val total: Int,
    @SerialName("totalHits") val totalHits: Int
)
