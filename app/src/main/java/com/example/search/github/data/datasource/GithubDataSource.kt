package com.example.search.github.data.datasource

import com.example.search.github.remote.model.GithubResponseModel

interface GithubDataSource {
    suspend fun getRepositories(query: String, page: Int): GithubResponseModel
}