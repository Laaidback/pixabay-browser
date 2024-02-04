package com.example.core.navigation

import androidx.lifecycle.SavedStateHandle


sealed class NavigationDestination(val route: String) {

    data object ImageSearch : NavigationDestination(route = "imageList")

    data object ImageDetails : NavigationDestination(route = "imageDetails/{$IMAGE_ID_KEY}") {

        fun withImageId(imageId: String): String {
            return route.replace("{$IMAGE_ID_KEY}", imageId)
        }

        fun extractImageId(savedStateHandle: SavedStateHandle): String {
            return savedStateHandle.get<String>(IMAGE_ID_KEY) ?: error("imageId not provided")
        }
    }

    companion object {
        const val IMAGE_ID_KEY = "imageId"
    }
}
