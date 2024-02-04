package com.example.imagedatasource.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ImageDao {

    @Upsert
    suspend fun saveImages(images: List<ImageEntity>)

    @Query("SELECT * FROM ImageEntity WHERE id = :imageId")
    suspend fun getImageById(imageId: String): ImageEntity

    @Upsert
    suspend fun saveSearchResult(searchResultEntity: SearchResultEntity)

    @Query("SELECT * FROM SearchResultEntity WHERE query = :query")
    suspend fun getSearchResultsFromQuery(query: String): SearchResultEntity?
}
