package com.example.search.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.search.github.ui.theme.SearchGithubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            SearchGithubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    SearchApp()
                }
            }
        }
    }


}

enum class AppRoutes {
    Search, Recent
}

@Composable
fun SearchApp(navController: NavHostController = rememberNavController()) {

    Scaffold { innerPadding ->

        val items = listOf(
            WithIcon.Search, WithIcon.Recent
        )

        NavHost(
            navController = navController,
            startDestination = AppRoutes.Search.name,
            modifier = Modifier.padding(innerPadding)
        ) {


            composable(AppRoutes.Search.name) {
                SearchScreen()
            }

            composable(AppRoutes.Recent.name) {
                RecentScreen()
            }
        }

        NavigationBar(modifier = Modifier.fillMaxWidth()) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                NavigationBarItem(icon = {
                    Icon(
                        painterResource(id = screen.iconRes),
                        contentDescription = screen.label()
                    )
                },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.tertiary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTextColor = MaterialTheme.colorScheme.tertiary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = Transparent
                    ),
                    label = {
                        Text(text = screen.label())
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route)
                    })
            }
        }
    }
}


@Composable
fun SearchScreen() {
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Search screen")
    }
}

@Composable
fun RecentScreen() {
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Recent screen")
    }
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SearchGithubTheme {
        Greeting("Android")
    }
}