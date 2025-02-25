package com.test.presentation.mapper

import com.test.domain.models.UserDomain
import com.test.domain.models.UserSearchDomain
import com.test.presentation.models.UserSearchUI
import com.test.presentation.models.UserUI

fun UserDomain?.toUiModel(): UserUI = this?.run {
    UserUI(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl,
        name = name,
        blog = blog,
        location = location,
        email = email,
        bio = bio,
        twitterUsername = twitterUsername,
        publicRepos = publicRepos.toString(),
        followers = followers.toString(),
        following = following.toString()
    )
} ?: UserUI()

fun List<UserSearchDomain>?.toListUI(): List<UserSearchUI> =
    this.orEmpty().map { it.toUiModel() }

fun UserSearchDomain?.toUiModel(): UserSearchUI = this?.run {
    UserSearchUI(
        id = id,
        username = login,
        avatarUrl = avatarUrl,
        linkGithub = htmlUrl
    )
} ?: UserSearchUI()