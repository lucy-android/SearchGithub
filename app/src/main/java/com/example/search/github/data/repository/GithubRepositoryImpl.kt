package com.example.search.github.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.search.github.data.datasource.GithubDataSource
import com.example.search.github.data.paging.GithubPagingSource
import com.example.search.github.domain.repository.GithubRepository
import com.example.search.github.remote.model.GithubRepositoryRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepositoryImpl @Inject constructor(private val githubDataSource: GithubDataSource) :
    GithubRepository {
    override suspend fun getRepositories(
        query: String
    ): Flow<PagingData<GithubRepositoryRemote>> {
        return Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                GithubPagingSource(githubDataSource, query)
            }).flow
    }
}