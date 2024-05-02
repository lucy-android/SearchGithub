package com.example.search.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.search.github.data.datasource.GithubDataSource
import com.example.search.github.remote.GithubApi
import com.example.search.github.ui.SearchApp
import com.example.search.github.ui.theme.SearchGithubTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var githubDataSource: GithubDataSource


    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            Log.d("APP_TAG", "onCreate: ${githubDataSource.getRepositories("abc", 1)}")

        }
        setContent {
            SearchGithubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    SearchApp()
                }
            }
        }
    }
}