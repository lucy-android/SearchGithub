package com.example.search.github.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.search.github.domain.model.GithubRepositoryDomain
import com.example.search.github.domain.usecase.FetchReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubReposViewModel @Inject constructor(private val fetchReposUseCase: FetchReposUseCase) :
    ViewModel() {

    private val _state: MutableStateFlow<PagingData<GithubRepositoryDomain>> =
        MutableStateFlow(PagingData.empty())
    val state: StateFlow<PagingData<GithubRepositoryDomain>> = _state

    fun loadData(s: String) {
        viewModelScope.launch(Dispatchers.Default) {
            fetchReposUseCase.fetchRepos(s).distinctUntilChanged().cachedIn(viewModelScope)
                .collectLatest {
                    _state.value = it
                }
        }
    }
}