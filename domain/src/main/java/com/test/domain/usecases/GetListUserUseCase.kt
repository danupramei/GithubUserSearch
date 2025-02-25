package com.test.domain.usecases

import com.test.domain.models.UserSearchDomain
import com.test.domain.repository.UsersRepository
import com.test.domain.utils.DomainResult
import javax.inject.Inject

class GetListUserUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(): DomainResult<List<UserSearchDomain>> {
        return userRepository.getListUser()
    }
}