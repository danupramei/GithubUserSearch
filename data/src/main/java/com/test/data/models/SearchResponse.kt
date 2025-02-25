package com.test.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean? = false,
    @Json(name = "items")
    val items: List<UserSearchResponse>? = listOf(),
    @Json(name = "total_count")
    val totalCount: Int? = 0
) {

    @JsonClass(generateAdapter = true)
    data class UserSearchResponse(
        @Json(name = "avatar_url")
        val avatarUrl: String? = "",
        @Json(name = "html_url")
        val htmlUrl: String? = "",
        @Json(name = "id")
        val id: Int? = 0,
        @Json(name = "login")
        val login: String? = "",
        @Json(name = "node_id")
        val nodeId: String? = "",
        @Json(name = "site_admin")
        val siteAdmin: Boolean? = false,
        @Json(name = "type")
        val type: String? = "",
        @Json(name = "url")
        val url: String? = ""
    )
}