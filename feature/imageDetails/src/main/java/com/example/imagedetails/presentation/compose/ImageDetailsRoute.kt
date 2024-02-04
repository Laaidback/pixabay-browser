package com.example.imagedetails.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imagedetails.presentation.ImageDetailsViewModel

@Composable
internal fun ImageDetailsRoute() {
    val viewModel: ImageDetailsViewModel = hiltViewModel()
    val uiModel by viewModel.uiModel.collectAsState()
    ImageDetailsScreen(uiModel)
}
