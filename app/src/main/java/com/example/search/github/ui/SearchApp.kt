package com.example.search.github.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.search.github.R
import com.example.search.github.ui.navigation.AppNavigation
import com.example.search.github.ui.navigation.WithIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchApp(navController: NavHostController = rememberNavController()) {
    Column {
        Surface(shadowElevation = 3.dp) {
            Scaffold(bottomBar = {
                NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.graphicsLayer { shadowElevation = 20f }) {

                    val items = listOf(
                        WithIcon.Search, WithIcon.Recent
                    )

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painterResource(id = screen.iconRes),
                                    contentDescription = screen.label()
                                )
                            },
                            label = {
                                Text(text = screen.label())
                            },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route)
                            })
                    }
                }

            }, topBar = {
                Column {
                    Surface(shadowElevation = 3.dp) {
                        CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            titleContentColor = MaterialTheme.colorScheme.secondary,
                        ), title = { Text(text = stringResource(id = R.string.app_name)) })
                    }
                }
            }
            ) { innerPadding ->
                AppNavigation(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}