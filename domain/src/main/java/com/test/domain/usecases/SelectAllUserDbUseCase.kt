package com.test.domain.usecases

import com.test.domain.models.UserSearchDomain
import com.test.domain.repository.UsersRepository
import javax.inject.Inject

class SelectAllUserDbUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(): List<UserSearchDomain> = userRepository.getAllUsers()
}