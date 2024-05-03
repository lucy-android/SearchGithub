package com.example.search.github.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.search.github.data.datasource.GithubDataSource
import com.example.search.github.remote.model.GithubRepositoryRemote
import retrofit2.HttpException
import java.io.IOException

class GithubPagingSource(
    private val remoteDataSource: GithubDataSource,
    private val query: String
) :
    PagingSource<Int, GithubRepositoryRemote>() {
    override fun getRefreshKey(state: PagingState<Int, GithubRepositoryRemote>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepositoryRemote> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getRepositories(
                query,
                page = pageNumber
            )
            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            val nextKey = if (response.items?.isNotEmpty() == true) pageNumber + 1 else null
            LoadResult.Page(
                data = response.items ?: emptyList(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}