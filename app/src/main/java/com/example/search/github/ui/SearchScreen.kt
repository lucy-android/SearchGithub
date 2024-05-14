package com.example.search.github.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.search.github.R
import com.example.search.github.domain.model.GithubRepo
import com.example.search.github.ui.viewmodel.GithubReposViewModel

@Composable
fun SearchScreen(viewModel: GithubReposViewModel = hiltViewModel()) {

    val repoPagingItems: LazyPagingItems<GithubRepo> =
        viewModel.state.collectAsLazyPagingItems()

    var textFieldValue by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.dp10))) {
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = { s ->
                textFieldValue = s
                viewModel.loadData(s)
            })

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            val loadState = repoPagingItems.loadState
            items(repoPagingItems.itemCount) { index ->
                val repo = repoPagingItems[index]
                if (repo != null) {
                    RepoItem(repo = repo)
                }
            }
            item {
                if(loadState.append is LoadState.Loading){
                    CircularProgressIndicator()
                }

                if (loadState.refresh is LoadState.Loading && textFieldValue.isNotEmpty()) {
                    CircularProgressIndicator()
                } else if(loadState.refresh is LoadState.Loading){
                    EmptyResult(
                        modifier = Modifier
                            .padding(vertical = dimensionResource(id = R.dimen.dp10))
                            .fillParentMaxSize(),
                        text = "Type something"
                    )
                }
                when (val refresh = loadState.refresh) {
                    is LoadState.Error -> {
                        EmptyResult(
                            modifier = Modifier
                                .padding(vertical = dimensionResource(id = R.dimen.dp10))
                                .fillParentMaxSize()
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}

@Composable
fun RepoItem(repo: GithubRepo) {
    Column {
        Row {
            Text(modifier = Modifier.weight(1f), text = repo.fullName)
            FilledIconButton(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.dp5))
                    .size(dimensionResource(id = R.dimen.dp30)),
                onClick = {
                    // TODO download action
                }, shape = CircleShape, colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_download),
                    contentDescription = null
                )
            }
        }
        Divider()
    }
}

@Composable
fun EmptyResult(modifier: Modifier = Modifier, text: String = "No results were obtained") {
    Text(text = text)
}