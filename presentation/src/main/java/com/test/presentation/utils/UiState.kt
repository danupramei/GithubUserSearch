package com.test.presentation.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.domain.utils.DomainResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class UiState<out T> {
    object Uninitialized : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Error(var message: String? = null) : UiState<Nothing>()
    object Empty : UiState<Nothing>()
    data class Success<out T>(val data: T?) : UiState<T>()
}

fun <T> UiState<T>.onLoading(
    execute: () -> Unit
): UiState<T> = apply {
    if (this is UiState.Loading) {
        execute()
    }
}

fun <T> UiState<T>.onError(
    execute: (String?) -> Unit
): UiState<T> = apply {
    if (this is UiState.Error) {
        execute(message)
    }
}

fun <T> UiState<T>.onSuccess(
    execute: (T?) -> Unit
): UiState<T> = apply {
    if (this is UiState.Success) {
        execute(data)
    }
}

fun <T> UiState<T>.onEmpty(
    execute: () -> Unit
): UiState<T> = apply {
    if (this is UiState.Empty) {
        execute.invoke()
    }
}

suspend fun <T> MutableStateFlow<UiState<T>>.mapToUiState(
    domainResult: DomainResult<T>,
    dispatcher: CoroutineDispatcher,
    onSuccessCheck: ((T) -> Unit)? = null
) {
    withContext(dispatcher) {
        emit(
            when (domainResult) {
                is DomainResult.Success -> {
                    val result = domainResult.data
                    onSuccessCheck?.let {
                        it.invoke(result)
                        UiState.Loading
                    } ?: run {
                        UiState.Success(result)
                    }
                }

                is DomainResult.EmptyState -> UiState.Empty
                is DomainResult.ErrorState -> UiState.Error(domainResult.message)
            }
        )
    }
}

fun <I, O> DomainResult<I?>.mapToModelUi(mapper: (I?) -> O): DomainResult<O> {
    return when (this) {
        is DomainResult.EmptyState -> DomainResult.EmptyState(this.message)
        is DomainResult.ErrorState -> DomainResult.ErrorState(
            this.message,
            this.responseStatusCode,
            mapper(this.data)
        )
        is DomainResult.Success -> DomainResult.Success(mapper(this.data))
    }
}

/**
 * this generic class to collect data from viewModel
 */
inline fun <T> Flow<T>.launchAndCollectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    crossinline action: suspend CoroutineScope.(T) -> Unit
) = owner.lifecycleScope.launch {
    owner.repeatOnLifecycle(minActiveState) {
        collect {
            action(it)
        }
    }
}
