package com.test.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserList(users: List<UserEntity>)

    @Query("SELECT * FROM user_github")
    fun getAllUsers(): List<UserEntity>

    @Query("DELETE FROM user_github")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM user_github")
    suspend fun getUserCount(): Int
}