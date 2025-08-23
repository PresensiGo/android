package com.rizalanggoro.presensigo.presentation.pages.student.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.openapi.apis.StudentApi
import com.rizalanggoro.presensigo.openapi.models.GetProfileStudentRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentProfileViewModel(
    private val tokenManager: TokenManager,
    private val studentApi: StudentApi
) : ViewModel() {
    private val _profileState = MutableStateFlow<UiState<GetProfileStudentRes>>(UiState.Loading)
    val profileState = _profileState.asStateFlow()

    fun getProfile() = viewModelScope.launch {
        try {
            _profileState.update { UiState.Loading }

            val body = studentApi.getProfileStudent().body()

            _profileState.update { UiState.Success(body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _profileState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _profileState.update { UiState.Failure() }
        }
    }

    private val _logoutState = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val logoutState = _logoutState.asStateFlow()

    fun logout() = viewModelScope.launch {
        _logoutState.update { UiState.Loading }
        tokenManager.clear()
        _logoutState.update { UiState.Success(Unit) }
    }

    init {
        getProfile()
    }
}