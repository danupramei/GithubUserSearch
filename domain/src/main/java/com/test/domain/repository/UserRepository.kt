package com.test.domain.repository

import com.test.domain.models.UserDomain
import com.test.domain.models.UserSearchDomain
import com.test.domain.utils.DomainResult

interface UsersRepository {
    suspend fun searchUsers(query: String): DomainResult<List<UserSearchDomain>>
    suspend fun getListUser(): DomainResult<List<UserSearchDomain>>
    suspend fun getUser(username: String): DomainResult<UserDomain>
    suspend fun insertUsersWithClear(users: List<UserSearchDomain>)
    suspend fun getAllUsers(): List<UserSearchDomain>
}