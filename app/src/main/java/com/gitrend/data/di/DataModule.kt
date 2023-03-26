package com.gitrend.data.di

import com.gitrend.data.GithubRepositoryImpl
import com.gitrend.domain.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun provideRepository(impl: GithubRepositoryImpl): GithubRepository

}