@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.imagesearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.coroutines.CoroutineContextOwner
import com.example.core.presentation.model.DialogModel
import com.example.core.presentation.model.InputModel
import com.example.imagedatasource.domain.model.Image
import com.example.imagesearch.domain.SearchImagesUseCase
import com.example.imagesearch.presentation.mapper.ImageUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ImageSearchViewModel @Inject constructor(
    private val searchImagesUseCase: SearchImagesUseCase,
    private val imageUiMapper: ImageUiMapper,
    private val coroutineContextOwner: CoroutineContextOwner,
) : ViewModel(), CoroutineContextOwner by coroutineContextOwner {

    private val _eventChannel = Channel<ImageSearchEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    private val _uiModel = MutableStateFlow(initialState)
    val uiModel: StateFlow<ImageSearchUiModel> = _uiModel

    private val initialState: ImageSearchUiModel
        get() = ImageSearchUiModel(
            searchInput = InputModel(
                value = INITIAL_SEARCH_INPUT,
                onInputChange = ::onNewInput
            ),
            contentResult = ContentResult.Loading,
            dialogModel = null,
        )

    init {
        viewModelScope.launch(ioCoroutineContext) {
            uiModel
                .map { uiModel -> uiModel.searchInput.value }
                .distinctUntilChanged()
                .onEach(::updateContentResultFromInput)
                .filter(CharSequence::isNotBlank)
                .debounce(INPUT_DEBOUNCE_MILLIS)
                .flatMapLatest(searchImagesUseCase::execute)
                .map(::mapResult)
                .collect(::setContentResult)
        }
    }

    private fun mapResult(result: Result<List<Image>>): ContentResult {
        return result.fold(
            onSuccess = { imageList ->
                if (imageList.isEmpty()) {
                    return ContentResult.EmptyResult
                }
                val uiItems = imageList.map { imageModel ->
                    imageUiMapper.mapToUI(
                        domainObject = imageModel,
                        onItemClick = ::onItemClick,
                        onChipClick = ::onNewInput,
                    )
                }
                ContentResult.Success(uiItems)
            },
            onFailure = { throwable ->
                ContentResult.Error(throwable.message.orEmpty())
            }
        )
    }

    private fun updateContentResultFromInput(searchInput: String) {
        setContentResult(
            contentResult = if (searchInput.isBlank()) ContentResult.EmptyInput else ContentResult.Loading
        )
    }

    private fun onItemClick(image: Image) {
        _uiModel.update { it.copy(dialogModel = createDialogModel(image)) }
    }

    private fun onNewInput(newInput: String) {
        _uiModel.update { it.copy(searchInput = it.searchInput.withNewInput(newInput)) }
    }

    private fun setContentResult(contentResult: ContentResult) {
        _uiModel.update { it.copy(contentResult = contentResult) }
    }

    private fun onDismissDialog() {
        _uiModel.update { it.copy(dialogModel = null) }
    }

    private fun createDialogModel(image: Image) = DialogModel(
        onDismiss = ::onDismissDialog,
        onConfirm = {
            onDismissDialog()
            viewModelScope.launch(mainCoroutineContext) {
                _eventChannel.send(ImageSearchEvent.OpenImageDetails(imageId = image.id))
            }
        }
    )
}

private const val INITIAL_SEARCH_INPUT = "fruits"
private const val INPUT_DEBOUNCE_MILLIS = 500L
