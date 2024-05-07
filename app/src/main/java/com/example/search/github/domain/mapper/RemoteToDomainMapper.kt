package com.example.search.github.domain.mapper

import com.example.search.github.domain.model.GithubRepo
import com.example.search.github.remote.model.GithubRepositoryRemote

fun GithubRepositoryRemote.toDomain() = GithubRepo(id, fullName)
