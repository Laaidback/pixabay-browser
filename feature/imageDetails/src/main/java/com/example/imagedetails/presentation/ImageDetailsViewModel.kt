package com.example.imagedetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.coroutines.CoroutineContextOwner
import com.example.core.navigation.NavigationDestination.ImageDetails.extractImageId
import com.example.imagedetails.domain.GetImageDetailsUseCase
import com.example.imagedetails.presentation.mapper.ImageDetailsUiModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    coroutineContextOwner: CoroutineContextOwner,
    private val getImageDetailsUseCase: GetImageDetailsUseCase,
    private val uiMapper: ImageDetailsUiModelMapper,
) : ViewModel(), CoroutineContextOwner by coroutineContextOwner {

    private val _uiModel = MutableStateFlow(initialState)
    val uiModel: StateFlow<ImageDetailsUiModel> = _uiModel

    private val initialState: ImageDetailsUiModel
        get() = ImageDetailsUiModel.Loading

    init {
        viewModelScope.launch {
            getImageDetailsUseCase.execute(imageId = extractImageId(savedStateHandle))
                .let(uiMapper::mapToUI)
                .let(ImageDetailsUiModel::ImageLoaded)
                .let { _uiModel.emit(it) }
        }
    }
}
