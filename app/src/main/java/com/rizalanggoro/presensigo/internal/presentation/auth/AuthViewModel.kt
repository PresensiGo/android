package com.rizalanggoro.presensigo.internal.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.internal.data.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.login(email, password)
        }
    }

    fun register() {}
}