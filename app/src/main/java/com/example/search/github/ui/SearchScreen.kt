package com.example.search.github.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.search.github.MainActivity
import com.example.search.github.R
import com.example.search.github.domain.model.GithubRepo
import com.example.search.github.ui.viewmodel.GithubReposViewModel
import com.example.search.github.utils.findActivity

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
                    RepoItem(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp), repo = repo
                    )
                }
            }
            item {
                if (loadState.append is LoadState.Loading) {
                    CircularProgressBar()
                }

                if (loadState.refresh is LoadState.Loading && textFieldValue.isNotEmpty()) {
                    CircularProgressBar()
                } else if (loadState.refresh is LoadState.Loading) {
                    EmptyResult(
                        modifier = Modifier
                            .padding(vertical = dimensionResource(id = R.dimen.dp10))
                            .fillParentMaxSize(),
                        text = stringResource(R.string.type_something)
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
fun RepoItem(modifier: Modifier = Modifier, repo: GithubRepo) {
    val context = LocalContext.current
    Column {
        Row {
            Text(modifier = modifier.weight(1f).align(Alignment.CenterVertically), text = repo.fullName)
            FilledIconButton(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.dp5))
                    .align(Alignment.CenterVertically)
                    .size(dimensionResource(id = R.dimen.dp30)),
                onClick = {
                    (context.findActivity() as MainActivity).beginDownload()
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
fun EmptyResult(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.no_results_were_obtained)
) {
    Text(modifier = modifier, text = text)
}

@Composable
fun CircularProgressBar(modifier: Modifier = Modifier){
    Box(
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.dp10))
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier)
    }
}