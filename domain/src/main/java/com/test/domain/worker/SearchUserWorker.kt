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
    private val searchUseCase: SearchUserUseCase,
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val query = inputData.getString("QUERY") ?: return Result.failure()
        return try {
            when (val response = searchUseCase.invoke(query)) {
                is DomainResult.Success -> {
                    val jsonUsers = Gson().toJson(response.data)
                    Result.success(Data.Builder().putString("RESULT", jsonUsers).build())
                }

                is DomainResult.EmptyState -> Result.success(
                    Data.Builder().putString("RESULT", "[]").build()
                )

                is DomainResult.ErrorState -> Result.failure(
                    Data.Builder().putString("ERROR", response.message).build()
                )
            }
        } catch (e: Exception) {
            Result.failure(Data.Builder().putString("ERROR", "Terjadi kesalahan").build())
        }
    }
}