package com.test.data.repository

import com.test.data.BuildConfig
import com.test.data.local.dao.UserDao
import com.test.data.mapper.toDomainModel
import com.test.data.mapper.toEntityModelList
import com.test.data.mapper.toListDomain
import com.test.data.network.processResponseSuspended
import com.test.data.services.GithubUserServices
import com.test.domain.models.UserDomain
import com.test.domain.models.UserSearchDomain
import com.test.domain.repository.UsersRepository
import com.test.domain.utils.DomainResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userServices: GithubUserServices,
    private val userDao: UserDao
) : UsersRepository {

    override suspend fun searchUsers(query: String): DomainResult<List<UserSearchDomain>> {
        val result = userServices.getUsersSearch(BuildConfig.API_KEY, query)
        return processResponseSuspended(result) {
            DomainResult.Success(it.toListDomain())
        }
    }

    override suspend fun getListUser(): DomainResult<List<UserSearchDomain>> {
        val result = userServices.getListUser(BuildConfig.API_KEY)
        return processResponseSuspended(result) {
            insertUsersWithClear(it.toListDomain())
            DomainResult.Success(it.toListDomain())
        }
    }

    override suspend fun getUser(username: String): DomainResult<UserDomain> {
        val result = userServices.getUser(username)
        return processResponseSuspended(result) {
            DomainResult.Success(it.toDomainModel())
        }
    }

    override suspend fun insertUsersWithClear(users: List<UserSearchDomain>) {
        if (userDao.getUserCount() > 0) {
            userDao.deleteAll()
        }
        userDao.insertUserList(users.toEntityModelList())
    }

    override suspend fun getAllUsers(): List<UserSearchDomain> {
        return userDao.getAllUsers().map { it.toDomainModel() }
    }
}