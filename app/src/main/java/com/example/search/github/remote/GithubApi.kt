package com.example.search.github.remote

import com.example.search.github.constants.ApiConstants
import com.example.search.github.remote.model.GithubResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("/search/repositories")
    suspend fun getRepositoriesList(
        @Query("q") searchText: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int = ApiConstants.itemsPerPage
    ): GithubResponseModel
}