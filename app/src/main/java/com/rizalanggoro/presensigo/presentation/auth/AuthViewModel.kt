package com.rizalanggoro.presensigo.presentation.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.data.repositories.AuthRepository
import com.rizalanggoro.presensigo.openapi.apis.AuthApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel(), KoinComponent {
    companion object {
        const val TAG = "AuthViewModel"
    }

    private var _uiState = MutableStateFlow(AuthState())
    val uiState get() = _uiState.asStateFlow()

    val apiClient by inject<AuthApi>()

    fun test() {
        Log.d(TAG, "test: ${apiClient}")
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(detail = AuthState.Detail.Loading) }
            authRepository.login(email, password)
                .onLeft {
                    _uiState.update { it.copy(detail = AuthState.Detail.Success) }
                }
                .onRight {
                    _uiState.update { it.copy(detail = AuthState.Detail.Failure) }
                }
        }
    }

    fun register() {}
}