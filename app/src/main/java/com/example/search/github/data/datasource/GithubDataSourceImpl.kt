package com.example.search.github.data.datasource

import com.example.search.github.remote.GithubApi
import com.example.search.github.remote.model.GithubResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubDataSourceImpl @Inject constructor(private val githubApi: GithubApi) :
    GithubDataSource {
    override suspend fun getRepositories(query: String, page: Int): GithubResponseModel {
        return githubApi.getRepositoriesList(query, page)
    }
}