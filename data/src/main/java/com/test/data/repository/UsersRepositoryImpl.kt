package com.test.data.repository

import com.test.data.mapper.toListDomain
import com.test.data.network.processResponseSuspended
import com.test.data.services.GithubUserServices
import com.test.domain.models.UserDomain
import com.test.domain.repository.UsersRepository
import com.test.domain.utils.DomainResult
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val userServices: GithubUserServices
) : UsersRepository {
    override suspend fun getListUser(query: String): DomainResult<List<UserDomain>> {
        val strApiKey = ""
        val result = userServices.getUsers(strApiKey, query)
        return processResponseSuspended(result) {
            DomainResult.Success(it.toListDomain())
        }
    }
}