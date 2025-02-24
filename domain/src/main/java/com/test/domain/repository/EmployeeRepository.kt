package com.test.domain.repository

import com.test.domain.models.UserDomain
import com.test.domain.utils.DomainResult

interface UsersRepository {
    suspend fun getListUser(query: String): DomainResult<List<UserDomain>>
}