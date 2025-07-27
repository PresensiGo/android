package com.rizalanggoro.presensigo.presentation.home.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.data.managers.TokenPayloadManager
import com.rizalanggoro.presensigo.data.repositories.AuthRepository
import com.rizalanggoro.presensigo.data.repositories.ResetRepository
import com.rizalanggoro.presensigo.domain.TokenPayload
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val action: Action = Action.Initial,
    val payload: TokenPayload = TokenPayload(),
) {
    enum class Action {
        Initial, Reset, Logout
    }
}

class HomeSettingViewModel(
    private val authRepository: AuthRepository,
    private val resetRepository: ResetRepository,
    private val tokenManager: TokenManager,
    private val tokenPayloadManager: TokenPayloadManager,
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    init {
        loadTokenPayload()
    }

    fun resetState() = _state.update {
        it.copy(
            status = StateStatus.Initial,
            action = State.Action.Initial
        )
    }

    private fun loadTokenPayload() = viewModelScope.launch {
        val token = tokenManager.get()
        if (token.accessToken.isNotEmpty()) {
            val payload = tokenPayloadManager.decode(token.accessToken)
            _state.update { it.copy(payload = payload) }
        }
    }

    fun reset() = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.Reset
            )
        }
        resetRepository.reset()
            .onLeft {
                _state.update { it.copy(status = StateStatus.Success) }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }

    fun logout() = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.Logout
            )
        }
        authRepository.logout()
            .onLeft {
                _state.update { it.copy(status = StateStatus.Success) }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }
}