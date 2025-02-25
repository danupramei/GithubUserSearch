package com.test.presentation.models

data class UserUI(
    val id: Int = 0,
    val login: String = "",
    val avatarUrl: String = "",
    val htmlUrl: String = "",
    val name: String = "",
    val blog: String = "",
    val location: String = "",
    val email: String = "",
    val bio: String = "",
    val twitterUsername: String = "",
    val publicRepos: String = "",
    val followers: String = "",
    val following: String = ""
)
