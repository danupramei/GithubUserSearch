package com.test.domain.models

data class UserSearchDomain(
    val avatarUrl: String = "",
    val htmlUrl: String = "",
    val id: Int = 0,
    val login: String = "",
    val nodeId: String = "",
    val siteAdmin: Boolean = false,
    val type: String = "",
    val url: String = ""
)