package com.example.imagesearch.presentation

sealed class ImageSearchEvent {
    data class OpenImageDetails(val imageId: String) : ImageSearchEvent()
}
