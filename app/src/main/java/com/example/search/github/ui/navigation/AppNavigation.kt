package com.example.search.github.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.search.github.R
import com.example.search.github.ui.RecentScreen
import com.example.search.github.ui.SearchScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Search.name,
        modifier = modifier
    ) {

        composable(AppRoutes.Search.name) {
            SearchScreen()
        }

        composable(AppRoutes.Recent.name) {
            RecentScreen()
        }
    }
}

enum class AppRoutes {
    Search, Recent
}


sealed class WithIcon(
    @DrawableRes val iconRes: Int, val route: String
) {
    data object Search : WithIcon(iconRes = R.drawable.ic_search, route = AppRoutes.Search.name) {
        override fun label() = "Search"
    }

    data object Recent : WithIcon(iconRes = R.drawable.ic_recent, route = AppRoutes.Recent.name) {
        override fun label() = "Recent"
    }

    abstract fun label(): String
}