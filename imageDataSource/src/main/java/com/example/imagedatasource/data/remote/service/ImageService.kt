package com.example.imagedatasource.data.remote.service

import com.example.imagedatasource.BuildConfig
import com.example.imagedatasource.data.remote.model.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ImageService {

    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY,
        @Query("q") query: String = "",
        @Query("id") id: String? = null,
    ): SearchImageResponse
}
