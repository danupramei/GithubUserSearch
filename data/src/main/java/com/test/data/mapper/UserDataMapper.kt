package com.test.data.mapper

import com.test.data.models.UserResponse
import com.test.domain.models.UserDomain

fun List<UserResponse>?.toListDomain(): List<UserDomain> =
    this.orEmpty().map { it.toDomainModel() }

fun UserResponse?.toDomainModel(): UserDomain = this?.run {
    UserDomain(
        id = id ?: 0,
        login = login.orEmpty(),
        avatarUrl = avatarUrl.orEmpty(),
        htmlUrl = htmlUrl.orEmpty(),
        name = name.orEmpty(),
        blog = blog.orEmpty(),
        location = location.orEmpty(),
        email = email.orEmpty(),
        bio = bio.orEmpty(),
        twitterUsername = twitterUsername.orEmpty(),
        publicRepos = publicRepos.orEmpty(),
        followers = followers.orEmpty(),
        following = following.orEmpty()
    )
} ?: UserDomain()