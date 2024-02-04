package com.example.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    navigationFactories: Set<NavigationFactory>,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        navigationFactories.forEach { navigationFactory ->
            navigationFactory.create(builder = this, navController = navController)
        }
    }
}
