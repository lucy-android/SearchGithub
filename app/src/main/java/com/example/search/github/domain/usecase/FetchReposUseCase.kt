package com.example.search.github.domain.usecase

import androidx.paging.PagingData
import com.example.search.github.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow

interface FetchReposUseCase {
    suspend fun fetchRepos(query: String): Flow<PagingData<GithubRepo>>

}