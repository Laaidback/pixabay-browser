package com.example.imagedetails.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.core.compose.TagList
import com.example.imagedetails.presentation.ImageDetails
import com.example.imagedetails.presentation.ImageDetailsUiModel

@Composable
@Preview
internal fun ImageDetailsScreen(
    @PreviewParameter(ImageDetailsUiModelPreview::class) uiModel: ImageDetailsUiModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiModel) {
            ImageDetailsUiModel.Loading -> Text(text = "loading")
            is ImageDetailsUiModel.ImageLoaded -> ImageDetails(uiModel.imageDetails)
        }
    }
}

@Composable
private fun ImageDetails(uiModel: ImageDetails) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        painter = rememberAsyncImagePainter(model = uiModel.imageUrl), contentDescription = null
    )
    Text(text = "User: ${uiModel.userName}")
    TagList(tags = uiModel.tags)
    ImageNumbers(uiModel.likesCount, uiModel.downloadsCount, uiModel.commentsCount)
}

@Composable
private fun ImageNumbers(likesCount: Int, downloadsCount: Int, commentsCount: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        ImageNumberItem(likesCount, "Likes")
        ImageNumberItem(downloadsCount, "Downloads")
        ImageNumberItem(commentsCount, "Comments")
    }
}

@Composable
private fun RowScope.ImageNumberItem(count: Int, title: String) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = title)
        Text(text = count.toString())
    }

}

private class ImageDetailsUiModelPreview : PreviewParameterProvider<ImageDetailsUiModel> {
    override val values: Sequence<ImageDetailsUiModel>
        get() = sequenceOf(
            ImageDetailsUiModel.ImageLoaded(
                imageDetails = ImageDetails(
                    userName = "userName",
                    imageUrl = "https://pixabay.com/get/ga5941c9723f9062629e4bd0e198ba7849d413df75dc5a22c79ad49c29bce958b75394eb0d9e2240dd782e3f0850dabff7489c8564bfc1e3ed497d65094990f02_1280.jpg",
                    tags = listOf("userName", "asd"),
                    likesCount = 111,
                    downloadsCount = 222,
                    commentsCount = 1231231231,
                )
            )
        )
}
