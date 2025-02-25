package com.test.data.repository

import com.test.data.BuildConfig
import com.test.data.mapper.toListDomain
import com.test.data.network.processResponseSuspended
import com.test.data.services.GithubUserServices
import com.test.domain.models.UserDomain
import com.test.domain.models.UserSearchDomain
import com.test.domain.repository.UsersRepository
import com.test.domain.utils.DomainResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userServices: GithubUserServices
) : UsersRepository {

    override suspend fun searchUsers(query: String): DomainResult<List<UserSearchDomain>> {
        val result = userServices.getUsersSearch(BuildConfig.API_KEY, query)
        return processResponseSuspended(result) {
            DomainResult.Success(it.toListDomain())
        }
    }

    override suspend fun getListUser(): DomainResult<List<UserDomain>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(username: String): DomainResult<UserDomain> {
        TODO("Not yet implemented")
    }
}