package com.test.presentation.views.listuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.squareup.moshi.Moshi
import com.test.domain.usecases.GetListUserUseCase
import com.test.domain.worker.SearchUserWorker
import com.test.presentation.mapper.toListUI
import com.test.presentation.models.UserItemUI
import com.test.presentation.utils.UiState
import com.test.presentation.utils.mapToModelUi
import com.test.presentation.utils.mapToUiState
import com.test.presentation.utils.observeListWorkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ListUserGithubViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val getListUserUseCase: GetListUserUseCase,
    moshi: Moshi
) : ViewModel() {
    private val _listUser =
        MutableStateFlow<UiState<List<UserItemUI>>>(UiState.Uninitialized)
    val listUser get() = _listUser.asStateFlow()

    private val _searchWorkId = MutableStateFlow<UUID?>(null)
    val searchWork: Flow<UiState<List<UserItemUI>>> =
        workManager.observeListWorkState(_searchWorkId, moshi) { userList ->
            if (userList.isNotEmpty()) UiState.Success(userList.toListUI()) else UiState.Empty
        }

    fun getListUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _listUser.emit(UiState.Loading)
            val result = getListUserUseCase().mapToModelUi { it.toListUI() }
            _listUser.mapToUiState(result, Dispatchers.Main)
        }
    }

    fun search(query: String) {
        val data = Data.Builder()
            .putString("QUERY", query)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<SearchUserWorker>()
            .setInputData(data)
            .setInitialDelay(800, TimeUnit.MILLISECONDS)
            .build()

        workManager.enqueueUniqueWork(
            "SEARCH_WORK",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        _searchWorkId.value = workRequest.id
    }
}