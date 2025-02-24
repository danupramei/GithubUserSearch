package com.test.data.services

import com.test.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GithubUserServices {
    @GET("search/users")
    suspend fun getUsers(
        @Header("Authorization") authorization: String,
        @Query("q") username: String
    ): Response<List<UserResponse>>
}