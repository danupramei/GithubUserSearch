package com.test.domain.utils

sealed class DomainResult<out T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class EmptyState(val message: String?, val responseStatusCode: Int? = 0) :
        DomainResult<Nothing>()
    data class ErrorState<T>(
        val message: String? = "",
        val responseStatusCode: Int? = 0,
        val data: T? = null
    ) : DomainResult<T>()
}
