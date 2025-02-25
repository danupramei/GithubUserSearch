package com.test.githubusersearch.di

import android.content.Context
import androidx.room.Room
import com.test.data.local.dao.UserDao
import com.test.data.local.db.UserGithubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserGithubDatabase(
        @ApplicationContext context: Context
    ): UserGithubDatabase {
        return Room.databaseBuilder(
            context,
            UserGithubDatabase::class.java,
            "github_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(
        db: UserGithubDatabase
    ): UserDao = db.userDao()
}