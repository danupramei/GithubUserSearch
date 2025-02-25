package com.test.presentation.utils

import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.util.UUID

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified T, R> WorkManager.observeWorkState(
    workIdFlow: Flow<UUID?>,
    moshi: Moshi,
    crossinline transform: (T) -> UiState<R>
): Flow<UiState<R>> {
    return workIdFlow.filterNotNull().flatMapLatest { id ->
        this.getWorkInfoByIdFlow(id).map { workInfo ->
            when (workInfo?.state) {
                WorkInfo.State.SUCCEEDED -> {
                    val jsonResult = workInfo.outputData.getString("RESULT") ?: "[]"
                    val type = Types.newParameterizedType(T::class.java)
                    val adapter: JsonAdapter<T> = moshi.adapter(type)

                    val data: T? = adapter.fromJson(jsonResult)
                    if (data != null) transform(data) else UiState.Empty
                }

                WorkInfo.State.FAILED -> {
                    val errorMsg = workInfo.outputData.getString("ERROR").orEmpty()
                    UiState.Error(errorMsg)
                }

                else -> UiState.Loading
            }
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified T, R> WorkManager.observeListWorkState(
    workIdFlow: Flow<UUID?>,
    moshi: Moshi,
    crossinline transform: (List<T>) -> UiState<R>
): Flow<UiState<R>> {
    return workIdFlow.filterNotNull().flatMapLatest { id ->
        this.getWorkInfoByIdFlow(id).map { workInfo ->
            when (workInfo?.state) {
                WorkInfo.State.SUCCEEDED -> {
                    val jsonResult = workInfo.outputData.getString("RESULT") ?: return@map UiState.Empty

                    // 🔹 Adapter untuk List<T>
                    val type = Types.newParameterizedType(List::class.java, T::class.java)
                    val adapter: JsonAdapter<List<T>> = moshi.adapter(type)

                    val data: List<T>? = adapter.fromJson(jsonResult)
                    if (data != null) transform(data) else UiState.Empty
                }

                WorkInfo.State.FAILED -> {
                    val errorMsg = workInfo.outputData.getString("ERROR").orEmpty()
                    UiState.Error(errorMsg)
                }

                else -> UiState.Loading
            }
        }
    }
}