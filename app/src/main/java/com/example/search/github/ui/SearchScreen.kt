package com.example.search.github.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.search.github.domain.model.GithubRepositoryDomain
import com.example.search.github.ui.viewmodel.GithubReposViewModel

@Composable
fun SearchScreen(viewModel: GithubReposViewModel = hiltViewModel()) {

    viewModel.loadData()

    val repoPagingItems: LazyPagingItems<GithubRepositoryDomain> =
        viewModel.state.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(repoPagingItems.itemCount){ index ->
            val repo = repoPagingItems[index]
            if (repo != null) {
                Text(repo.fullName)
            }
        }
    }
}