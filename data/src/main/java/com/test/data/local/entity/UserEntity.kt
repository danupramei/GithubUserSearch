package com.test.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_github")
data class UserEntity(
    @PrimaryKey()
    @ColumnInfo(name = "userId")
    val userId: Int? = 0,
    @ColumnInfo(name= "username")
    val username: String? = "",
    @ColumnInfo(name = "githubUrl")
    val githubUrl: String? = "",
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String? = ""
)