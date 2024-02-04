package com.example.imagesearch.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.navigation.NavigationDestination
import com.example.core.utils.ObserveEvents
import com.example.imagesearch.presentation.ImageSearchEvent
import com.example.imagesearch.presentation.ImageSearchViewModel

@Composable
fun ImageSearchRoute(navController: NavHostController) {
    val viewModel: ImageSearchViewModel = hiltViewModel()
    val uiModel by viewModel.uiModel.collectAsState()
    ObserveEvents(flow = viewModel.eventChannel) { event ->
        when (event) {
            is ImageSearchEvent.OpenImageDetails -> navController.navigate(
                route = NavigationDestination.ImageDetails.withImageId(event.imageId)
            )
        }
    }
    ImageSearchScreen(uiModel)
}
