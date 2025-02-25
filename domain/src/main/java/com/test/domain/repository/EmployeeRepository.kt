package com.test.domain.repository

import com.test.domain.models.UserDomain
import com.test.domain.models.UserSearchDomain
import com.test.domain.utils.DomainResult

interface UsersRepository {
    suspend fun searchUsers(query: String): DomainResult<List<UserSearchDomain>>
    suspend fun getListUser(): DomainResult<List<UserDomain>>
    suspend fun getUser(username: String): DomainResult<UserDomain>
}