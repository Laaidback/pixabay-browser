package com.example.pixabaybrowser.boot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.NavigationHost
import com.example.core.navigation.NavigationDestination
import com.example.core.navigation.NavigationFactory
import com.example.pixabaybrowser.ui.theme.PixabayBrowserTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationFactories: Set<@JvmSuppressWildcards NavigationFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixabayBrowserTheme {
                val navController = rememberNavController()
                NavigationHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = NavigationDestination.ImageSearch.route,
                    navigationFactories = navigationFactories,
                )
            }
        }
    }
}
