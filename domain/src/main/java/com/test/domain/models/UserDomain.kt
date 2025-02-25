package com.test.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDomain(
    val avatarUrl: String = "",
    val bio: String = "",
    val blog: String = "",
    val company: String = "",
    val createdAt: String = "",
    val email: String = "",
    val eventsUrl: String = "",
    val followers: Int = 0,
    val followersUrl: String = "",
    val following: Int = 0,
    val followingUrl: String = "",
    val gistsUrl: String = "",
    val gravatarId: String = "",
    val hireable: Boolean = false,
    val htmlUrl: String = "",
    val id: Int = 0,
    val location: String = "",
    val login: String = "",
    val name: String = "",
    val nodeId: String = "",
    val organizationsUrl: String = "",
    val publicGists: Int = 0,
    val publicRepos: Int = 0,
    val receivedEventsUrl: String = "",
    val reposUrl: String = "",
    val siteAdmin: Boolean = false,
    val starredUrl: String = "",
    val subscriptionsUrl: String = "",
    val twitterUsername: String = "",
    val type: String = "",
    val updatedAt: String = "",
    val url: String = ""
)