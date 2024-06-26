package com.example.search.github.di

import com.example.search.github.data.datasource.GithubDataSource
import com.example.search.github.data.datasource.GithubDataSourceImpl
import com.example.search.github.domain.repository.GithubRepository
import com.example.search.github.data.repository.GithubRepositoryImpl
import com.example.search.github.domain.usecase.FetchReposUseCase
import com.example.search.github.domain.usecase.FetchReposUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideDataSource(githubDataSourceImpl: GithubDataSourceImpl): GithubDataSource

    @Binds
    @Singleton
    abstract fun provideRepository(githubRepositoryImpl: GithubRepositoryImpl): GithubRepository

    @Binds
    @Singleton
    abstract fun provideUseCase(fetchReposUseCaseImpl: FetchReposUseCaseImpl): FetchReposUseCase

}