package com.test.data.network

import com.test.domain.utils.DomainResult
import retrofit2.Response

suspend fun <Input, Output> processResponseSuspended(
    result: Response<Input>,
    successBlock: suspend (Input?) -> DomainResult<Output>,
): DomainResult<Output> {
    return if (result.isSuccessful) successBlock(result.body()) else exceptionMapper(result)
}

fun <Input, Output> exceptionMapper(result: Response<Output>): DomainResult<Input> {
    val emptyStateErrorCodes = listOf(422, 404, 403, 409, 406, 401, 400)

    return if (emptyStateErrorCodes.contains(result.code())) {
        DomainResult.EmptyState(
            result.message(),
            result.code()
        )
    } else DomainResult.ErrorState(result.message(), result.code())
}