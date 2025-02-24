package com.test.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "login") val login: String? = "",
    @Json(name = "avatar_url") val avatarUrl: String? = "",
    @Json(name = "html_url") val htmlUrl: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "blog") val blog: String? = "",
    @Json(name = "location") val location: String? = "",
    @Json(name = "email") val email: String? = "",
    @Json(name = "bio") val bio: String? = "",
    @Json(name = "twitter_username") val twitterUsername: String? = "",
    @Json(name = "public_repos") val publicRepos: String? = "",
    @Json(name = "followers") val followers: String? = "",
    @Json(name = "following") val following: String? = ""
)