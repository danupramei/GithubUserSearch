package com.test.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.data.local.dao.UserDao
import com.test.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserGithubDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}