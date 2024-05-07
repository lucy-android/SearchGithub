package com.example.search.github.domain.repository

import androidx.paging.PagingData
import com.example.search.github.remote.model.GithubRepositoryRemote
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getRepos(query: String): Flow<PagingData<GithubRepositoryRemote>>
}