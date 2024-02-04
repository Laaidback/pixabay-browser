package com.example.imagesearch.presentation

import com.example.core.navigation.NavigationFactory
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core.navigation.NavigationDestination
import com.example.imagesearch.presentation.compose.ImageSearchRoute

class ImageSearchNavigationFactory : NavigationFactory {

    override fun create(builder: NavGraphBuilder, navController: NavHostController) = with (builder) {
        composable(route = NavigationDestination.ImageSearch.route) {
            ImageSearchRoute(navController)
        }
    }
}
