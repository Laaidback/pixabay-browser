package com.example.imagedatasource.domain.model

data class Image(
    val id: String,
    val userName: String,
    val previewURL: String,
    val largeImageURL: String,
    val tags: List<String>,
    val likesCount: Int,
    val downloadsCount: Int,
    val commentsCount: Int,
)
