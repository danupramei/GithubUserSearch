package com.test.githubusersearch.di

import com.test.data.services.GithubUserServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    @Singleton
    fun provideGithubUserServices(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): GithubUserServices {
        return retrofitBuilder.client(okHttpClient).baseUrl("https://api.github.com/").build()
            .create(GithubUserServices::class.java)
    }
}