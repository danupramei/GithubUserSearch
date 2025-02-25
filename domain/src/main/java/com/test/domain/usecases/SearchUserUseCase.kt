package com.test.domain.usecases

import com.test.domain.models.UserSearchDomain
import com.test.domain.repository.UsersRepository
import com.test.domain.utils.DomainResult
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(query: String): DomainResult<List<UserSearchDomain>> {
        return userRepository.searchUsers(query)
    }
}