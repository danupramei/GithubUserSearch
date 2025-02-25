package com.test.githubusersearch.di

import com.test.data.repository.UsersRepositoryImpl
import com.test.data.services.GithubUserServices
import com.test.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideEmployeeRepository(apiService: GithubUserServices): UsersRepository {
        return UsersRepositoryImpl(apiService)
    }
}