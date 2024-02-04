package com.example.imagedatasource.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchResultEntity(
    @PrimaryKey val query: String,
    val ids: List<String>,
)
