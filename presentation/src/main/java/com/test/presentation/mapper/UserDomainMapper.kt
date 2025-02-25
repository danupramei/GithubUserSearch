package com.test.presentation.mapper

import com.test.domain.models.UserDomain
import com.test.domain.models.UserSearchDomain
import com.test.presentation.models.UserItemUI
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
        following = following.toString(),
        company = company
    )
} ?: UserUI()

fun List<UserSearchDomain>?.toListUI(): List<UserItemUI> =
    this.orEmpty().map { it.toUiModel() }

fun UserSearchDomain?.toUiModel(): UserItemUI = this?.run {
    UserItemUI(
        id = id,
        username = login,
        avatarUrl = avatarUrl,
        linkGithub = htmlUrl
    )
} ?: UserItemUI()