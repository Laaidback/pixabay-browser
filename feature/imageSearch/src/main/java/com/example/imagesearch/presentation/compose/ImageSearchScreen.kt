@file:OptIn(ExperimentalLayoutApi::class)

package com.example.imagesearch.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.imagesearch.R
import com.example.imagesearch.presentation.ContentResult
import com.example.imagesearch.presentation.ImageSearchResultModel
import com.example.imagesearch.presentation.ImageSearchUiModel

@Composable
@Preview
fun ImageSearchScreen(
    @PreviewParameter(ImageSearchScreenPreviewParameterProvider::class) uiModel: ImageSearchUiModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchField(uiModel)
        SearchResult(uiModel.contentResult)
    }
    uiModel.dialogModel?.let { dialogModel ->
        ConfirmShowDetailsDialog(dialogModel)
    }
}

@Composable
private fun SearchField(
    uiModel: ImageSearchUiModel,
) {
    val searchFieldContentDescription = stringResource(R.string.search_field_content_description)
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = searchFieldContentDescription
            },
        value = uiModel.searchInput,
        onValueChange = { uiModel.onInputChange(it) },
    )
}

@Composable
private fun SearchResult(contentResult: ContentResult) {
    when (contentResult) {
        ContentResult.EmptyInput -> Text("to see results start typing in the field above")
        ContentResult.EmptyResult -> Text("no results for this query")
        ContentResult.Loading -> ProgressLoader()
        is ContentResult.Error -> Text("error: ${contentResult.message}")
        is ContentResult.Success -> ImageList(contentResult.items)
    }
}

@Composable
private fun ProgressLoader() {
    val progressIndicatorContentDescription =
        stringResource(R.string.progress_indicator_content_description)
    CircularProgressIndicator(
        modifier = Modifier.semantics {
            contentDescription = progressIndicatorContentDescription
        }
    )
}

private class ImageSearchScreenPreviewParameterProvider :
    PreviewParameterProvider<ImageSearchUiModel> {

    override val values: Sequence<ImageSearchUiModel>
        get() = sequenceOf(
            ImageSearchUiModel(
                searchInput = "searchInput",
                onInputChange = {},
                contentResult = ContentResult.Success(
                    items = listOf(
                        ImageSearchResultModel(
                            id = "id1",
                            userName = "userName",
                            imageUrl = "https://cdn.pixabay.com/photo/2017/05/13/17/31/fruit-2310212_150.jpg",
                            tags = listOf("userName", "tags"),
                            onClick = {},
                        ),
                        ImageSearchResultModel(
                            id = "id2",
                            userName = "userName2",
                            imageUrl = "https://cdn.pixabay.com/photo/2017/01/20/15/12/oranges-1995079_150.jpg",
                            tags = listOf("userName, tags, 2"),
                            onClick = {},
                        ),
                    )
                ),
                dialogModel = null,
            )
        )
}
