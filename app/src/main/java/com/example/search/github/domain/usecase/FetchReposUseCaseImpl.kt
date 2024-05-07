package com.example.search.github.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.search.github.domain.mapper.toDomain
import com.example.search.github.domain.model.GithubRepo
import com.example.search.github.domain.repository.GithubRepository
import com.example.search.github.remote.model.GithubRepositoryRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchReposUseCaseImpl @Inject constructor(private val githubRepository: GithubRepository) :
    FetchReposUseCase {
    override suspend fun fetchRepos(query: String): Flow<PagingData<GithubRepo>> {
        return githubRepository.getRepos(query).map { data -> data.map(GithubRepositoryRemote::toDomain) }
    }
}