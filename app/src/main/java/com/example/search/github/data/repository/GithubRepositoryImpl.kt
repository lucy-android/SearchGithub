package com.example.search.github.data.repository

import okhttp3.ResponseBody

class GithubRepositoryImpl: GithubRepository {
    override suspend fun getRepositories(query: String, page: Int): ResponseBody {
        TODO("Not yet implemented")
    }
}