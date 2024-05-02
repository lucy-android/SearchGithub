package com.example.search.github.remote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubResponseModel(
    @JsonProperty("total_count") val totalCount: Int,
    @JsonProperty("items") val items: List<GithubRepositoryRemote>?
)