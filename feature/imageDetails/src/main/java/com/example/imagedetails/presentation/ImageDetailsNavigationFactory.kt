package com.example.imagedetails.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.navigation.NavigationDestination
import com.example.core.navigation.NavigationDestination.Companion.IMAGE_ID_KEY
import com.example.core.navigation.NavigationFactory
import com.example.imagedetails.presentation.compose.ImageDetailsRoute

internal class ImageDetailsNavigationFactory : NavigationFactory {

    override fun create(builder: NavGraphBuilder, navController: NavHostController) =
        with(builder) {
            composable(
                route = NavigationDestination.ImageDetails.route,
                arguments = listOf(navArgument(IMAGE_ID_KEY) { type = NavType.StringType })
            ) {
                ImageDetailsRoute()
            }
        }
}
