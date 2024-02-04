package com.example.imagesearch.presentation


data class ImageSearchUiModel(
    val searchInput: String,
    val onInputChange: (String) -> Unit,
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
    val tags: List<String>,
    val onClick: () -> Unit,
)
data class DialogModel(
    val onDismiss: () -> Unit,
    val onConfirm: () -> Unit,
)
