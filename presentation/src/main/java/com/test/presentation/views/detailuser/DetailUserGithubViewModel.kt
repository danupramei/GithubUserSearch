package com.test.presentation.views.detailuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.usecases.GetDetailUserUseCase
import com.test.presentation.mapper.toUiModel
import com.test.presentation.models.UserUI
import com.test.presentation.utils.UiState
import com.test.presentation.utils.mapToModelUi
import com.test.presentation.utils.mapToUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserGithubViewModel @Inject constructor(
    private val getDetailUserUseCase: GetDetailUserUseCase
) : ViewModel() {
    private val _detailUser =
        MutableStateFlow<UiState<UserUI>>(UiState.Uninitialized)
    val detailUser get() = _detailUser.asStateFlow()

    fun getDetailUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailUser.emit(UiState.Loading)
            val result = getDetailUserUseCase(username).mapToModelUi { it.toUiModel() }
            _detailUser.mapToUiState(result, Dispatchers.Main)
        }
    }
}