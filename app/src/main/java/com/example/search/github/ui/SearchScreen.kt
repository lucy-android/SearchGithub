package com.example.search.github.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.search.github.domain.model.GithubRepo
import com.example.search.github.ui.viewmodel.GithubReposViewModel

@Composable
fun SearchScreen(viewModel: GithubReposViewModel = hiltViewModel()) {

    val repoPagingItems: LazyPagingItems<GithubRepo> =
        viewModel.state.collectAsLazyPagingItems()

    var textFieldValue by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(value = textFieldValue, onValueChange = { s ->
            textFieldValue = s
            viewModel.loadData(s)
        })

        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
        ) {
            items(repoPagingItems.itemCount) { index ->
                val repo = repoPagingItems[index]
                if (repo != null) {
                    Text(repo.fullName)
                }
            }
        }
    }
}