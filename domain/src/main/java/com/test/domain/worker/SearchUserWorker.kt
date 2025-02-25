package com.test.domain.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.test.domain.usecases.SearchUserUseCase
import com.test.domain.utils.DomainResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SearchUserWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val searchUseCase: SearchUserUseCase
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val query = inputData.getString("QUERY") ?: return Result.failure()

        return try {
            when (val response = searchUseCase.invoke(query)) {
                is DomainResult.Success -> {
                    val users = response.data
                    val jsonUsers = Gson().toJson(users)
                    val outputData = Data.Builder()
                        .putString("RESULT", jsonUsers)
                        .build()
                    Result.success(outputData)
                }

                is DomainResult.EmptyState -> {
                    val outputData = Data.Builder()
                        .putString("RESULT", "[]")
                        .build()
                    Result.success(outputData)
                }

                is DomainResult.ErrorState -> {
                    val errorData = Data.Builder()
                        .putString("ERROR", response.message)
                        .build()
                    Result.failure(errorData)
                }
            }
        } catch (e: Exception) {
            val errorData = Data.Builder()
                .putString("ERROR", "Terjadi kesalahan saat memproses pencarian")
                .build()
            Result.failure(errorData)
            Result.failure()
        }
    }
}