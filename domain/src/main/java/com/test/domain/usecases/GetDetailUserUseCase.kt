package com.test.domain.usecases

import com.test.domain.models.UserDomain
import com.test.domain.repository.UsersRepository
import com.test.domain.utils.DomainResult
import javax.inject.Inject

class GetDetailUserUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(username: String): DomainResult<UserDomain> {
        return userRepository.getUser(username)
    }
}