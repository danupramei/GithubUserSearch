package com.test.data.services

import com.test.data.models.SearchResponse
import com.test.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserServices {
    @GET("search/users")
    suspend fun getUsersSearch(
        @Header("Authorization") authorization: String,
        @Query("q") username: String
    ): Response<SearchResponse>

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): Response<UserResponse>

    @GET("users")
    suspend fun getListUser(
        @Header("Authorization") authorization: String
    ): Response<List<SearchResponse.UserSearchResponse>>
}