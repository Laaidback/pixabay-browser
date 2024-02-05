package com.example.imagesearch.presentation

import com.example.core.presentation.model.ChipModel
import com.example.core.presentation.model.DialogModel
import com.example.core.presentation.model.InputModel

data class ImageSearchUiModel(
    val searchInput: InputModel,
    val contentResult: ContentResult,
    val dialogModel: DialogModel?
)

sealed class ContentResult {
    data object EmptyInput : ContentResult()
    data object EmptyResult : ContentResult()
    data object Loading : ContentResult()
    data class Success(
        val items: List<ImageSearchResultModel>
    ) : ContentResult()

    data class Error(
        val message: String
    ) : ContentResult()
}

data class ImageSearchResultModel(
    val id: String,
    val userName: String,
    val imageUrl: String,
    val tags: List<ChipModel>,
    val onClick: () -> Unit,
)
