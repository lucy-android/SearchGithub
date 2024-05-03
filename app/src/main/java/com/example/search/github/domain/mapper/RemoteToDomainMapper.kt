package com.example.search.github.domain.mapper

import com.example.search.github.domain.model.GithubRepositoryDomain
import com.example.search.github.remote.model.GithubRepositoryRemote

fun GithubRepositoryRemote.toDomain() = GithubRepositoryDomain(id, fullName)
