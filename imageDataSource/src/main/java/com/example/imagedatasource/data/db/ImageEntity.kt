package com.example.imagedatasource.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "previewURL") val previewURL: String,
    @ColumnInfo(name = "imageUrl") val largeImageURL: String,
    @ColumnInfo(name = "tags") val tags: String,
    @ColumnInfo(name = "likesCount") val likesCount: Int,
    @ColumnInfo(name = "downloadsCount") val downloadsCount: Int,
    @ColumnInfo(name = "commentsCount") val commentsCount: Int,
)
