package com.example.search.github.data.datasource

import com.example.search.github.remote.GithubResponseModel

interface GithubDataSource {
    suspend fun getRepositories(query: String, page: Int): GithubResponseModel
}