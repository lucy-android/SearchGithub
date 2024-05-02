package com.example.search.github.remote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubRepositoryRemote(
    @JsonProperty("id") val id: Long,
    @JsonProperty("full_name") val fullName: String
)