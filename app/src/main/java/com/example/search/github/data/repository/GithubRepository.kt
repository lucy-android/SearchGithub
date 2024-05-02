package com.example.search.github.data.repository

import androidx.paging.PagingData
import com.example.search.github.remote.GithubRepositoryRemote
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface GithubRepository {
    suspend fun getRepositories(query: String, page: Int): Flow<PagingData<GithubRepositoryRemote>>
}