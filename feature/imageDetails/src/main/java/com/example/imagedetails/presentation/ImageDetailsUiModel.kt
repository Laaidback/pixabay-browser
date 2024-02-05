package com.example.imagedetails.presentation

import androidx.compose.runtime.Immutable
import com.example.core.presentation.model.ChipModel

sealed class ImageDetailsUiModel {

    data object Loading : ImageDetailsUiModel()

    data class ImageLoaded(val imageDetails: ImageDetails) : ImageDetailsUiModel()
}

@Immutable
data class ImageDetails(
    val userName: String,
    val imageUrl: String,
    val tags: List<ChipModel>,
    val likesCount: Int,
    val downloadsCount: Int,
    val commentsCount: Int,
)
