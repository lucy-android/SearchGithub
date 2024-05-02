package com.example.search.github.data.repository

import okhttp3.ResponseBody

interface GithubRepository {
    suspend fun getRepositories(query: String, page: Int): ResponseBody
}