package com.example.imagesearch.presentation.compose

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.imagesearch.R
import com.example.imagesearch.presentation.ContentResult
import com.example.imagesearch.presentation.ImageSearchResultModel
import com.example.imagesearch.presentation.ImageSearchUiModel
import org.junit.Rule
import org.junit.Test

class ImageSearchScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun imageSearchScreen_shouldPresentSearchField() {
        // Given
        val mockModel = ImageSearchUiModel(
            searchInput = "searchInput",
            onInputChange = {},
            contentResult = ContentResult.Loading,
            dialogModel = null,
        )

        // When
        composeTestRule.setContent {
            ImageSearchScreen(uiModel = mockModel)
        }

        // Then
        composeTestRule
            .onNodeWithContentDescription(
                label = composeTestRule.activity.getString(R.string.search_field_content_description)
            ).assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription(
                label = composeTestRule.activity.getString(R.string.progress_indicator_content_description)
            ).assertIsDisplayed()
    }

    @Test
    fun imageSearchScreen_shouldPresentAllResultImages() {
        // Given
        val items = listOf(
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
        val mockModel = ImageSearchUiModel(
            searchInput = "searchInput",
            onInputChange = {},
            contentResult = ContentResult.Success(items),
            dialogModel = null,
        )

        // When
        composeTestRule.setContent {
            ImageSearchScreen(uiModel = mockModel)
        }

        // Then
        composeTestRule
            .onAllNodesWithContentDescription(
                label = composeTestRule.activity.getString(R.string.image_content_description)
            ).assertCountEquals(items.size)
    }
}
