package com.test.presentation.views.listuser

import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.test.domain.models.UserSearchDomain
import com.test.domain.worker.SearchUserWorker
import com.test.presentation.mapper.toListUI
import com.test.presentation.models.UserSearchUI
import com.test.presentation.utils.UiState
import com.test.presentation.utils.observeWorkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ListUserGithubViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {
    private val _searchWorkId = MutableStateFlow<UUID?>(null)
    val searchWork: Flow<UiState<List<UserSearchUI>>> =
        workManager.observeWorkState(_searchWorkId) { userList: List<UserSearchDomain> ->
            if (userList.isNotEmpty()) UiState.Success(userList.toListUI()) else UiState.Empty
        }

    fun search(query: String) {
        val data = Data.Builder()
            .putString("QUERY", query)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<SearchUserWorker>()
            .setInputData(data)
            .build()

        workManager.enqueueUniqueWork(
            "SEARCH_WORK",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        _searchWorkId.value = workRequest.id
    }
}