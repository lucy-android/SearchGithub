package com.example.search.github.di

import com.example.search.github.data.datasource.GithubDataSource
import com.example.search.github.data.datasource.GithubDataSourceImpl
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


}