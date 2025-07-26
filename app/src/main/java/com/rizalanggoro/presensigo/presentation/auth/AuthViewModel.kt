package com.rizalanggoro.presensigo.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial
)

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    companion object {
        const val TAG = "AuthViewModel"
    }

    private var _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        _state.update { it.copy(status = StateStatus.Loading) }
        authRepository.login(email = email, password = password)
            .onLeft {
                _state.update { it.copy(status = StateStatus.Success) }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }

    fun register() {}
}