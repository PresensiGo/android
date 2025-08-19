package com.rizalanggoro.presensigo.presentation.pages.student.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.managers.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val logoutStatus: StateStatus = StateStatus.Initial
)

class ProfileStudentViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun logout() = viewModelScope.launch {
        _state.update { it.copy(logoutStatus = StateStatus.Loading) }
        tokenManager.clear()
        _state.update { it.copy(logoutStatus = StateStatus.Success) }
    }
}