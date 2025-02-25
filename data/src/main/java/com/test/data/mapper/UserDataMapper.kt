package com.test.data.mapper

import com.test.data.models.SearchResponse
import com.test.data.models.UserResponse
import com.test.domain.models.UserDomain
import com.test.domain.models.UserSearchDomain

fun SearchResponse?.toListDomain(): List<UserSearchDomain> = this?.run {
    items.orEmpty().map { it.toDomainModel() }
} ?: listOf()

fun SearchResponse.UserSearchResponse?.toDomainModel(): UserSearchDomain = this?.run {
    UserSearchDomain(
        avatarUrl = avatarUrl.orEmpty(),
        eventsUrl = eventsUrl.orEmpty(),
        followersUrl = followersUrl.orEmpty(),
        followingUrl = followingUrl.orEmpty(),
        gistsUrl = gistsUrl.orEmpty(),
        gravatarId = gravatarId.orEmpty(),
        htmlUrl = htmlUrl.orEmpty(),
        id = id ?: 0,
        login = login.orEmpty(),
        nodeId = nodeId.orEmpty(),
        organizationsUrl = organizationsUrl.orEmpty(),
        receivedEventsUrl = receivedEventsUrl.orEmpty(),
        reposUrl = reposUrl.orEmpty(),
        siteAdmin = siteAdmin ?: false,
        starredUrl = starredUrl.orEmpty(),
        subscriptionsUrl = subscriptionsUrl.orEmpty(),
        type = type.orEmpty(),
        url = url.orEmpty()
    )
} ?: UserSearchDomain()

fun List<UserResponse>?.toListDomain(): List<UserDomain> =
    this.orEmpty().map { it.toDomainModel() }

fun UserResponse?.toDomainModel(): UserDomain = this?.run {
    UserDomain(
        avatarUrl = avatarUrl.orEmpty(),
        bio = bio.orEmpty(),
        blog = blog.orEmpty(),
        company = company.orEmpty(),
        createdAt = createdAt.orEmpty(),
        email = email.orEmpty(),
        eventsUrl = eventsUrl.orEmpty(),
        followers = followers ?: 0,
        followersUrl = followersUrl.orEmpty(),
        following = following ?: 0,
        followingUrl = followingUrl.orEmpty(),
        gistsUrl = gistsUrl.orEmpty(),
        gravatarId = gravatarId.orEmpty(),
        hireable = hireable ?: false,
        htmlUrl = htmlUrl.orEmpty(),
        id = id ?: 0,
        location = location.orEmpty(),
        login = login.orEmpty(),
        name = name.orEmpty(),
        nodeId = nodeId.orEmpty(),
        organizationsUrl = organizationsUrl.orEmpty(),
        publicGists = publicGists ?: 0,
        publicRepos = publicRepos ?: 0,
        receivedEventsUrl = receivedEventsUrl.orEmpty(),
        reposUrl = reposUrl.orEmpty(),
        siteAdmin = siteAdmin ?: false,
        starredUrl = starredUrl.orEmpty(),
        subscriptionsUrl = subscriptionsUrl.orEmpty(),
        twitterUsername = twitterUsername.orEmpty(),
        type = type.orEmpty(),
        updatedAt = updatedAt.orEmpty(),
        url = url.orEmpty()
    )
} ?: UserDomain()