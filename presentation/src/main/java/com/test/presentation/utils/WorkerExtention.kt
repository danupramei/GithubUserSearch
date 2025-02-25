package com.test.presentation.utils

import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.util.UUID

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified T, R> WorkManager.observeWorkState(
    workIdFlow: Flow<UUID?>,
    crossinline transform: (T) -> UiState<R>
): Flow<UiState<R>> {
    return workIdFlow.filterNotNull().flatMapLatest { id ->
        this.getWorkInfoByIdFlow(id).map { workInfo ->
            when (workInfo?.state) {
                WorkInfo.State.SUCCEEDED -> {
                    val jsonResult = workInfo.outputData.getString("RESULT") ?: "[]"
                    val data: T = Gson().fromJson(jsonResult, object : TypeToken<T>() {}.type)
                    transform(data)
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