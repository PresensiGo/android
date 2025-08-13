package com.rizalanggoro.presensigo.presentation.pages.home.teacher.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.data.managers.TokenPayloadManager
import com.rizalanggoro.presensigo.data.repositories.AuthRepository
import com.rizalanggoro.presensigo.data.repositories.ResetRepository
import com.rizalanggoro.presensigo.domain.TeacherTokenPayload
import com.rizalanggoro.presensigo.openapi.apis.AccountApi
import com.rizalanggoro.presensigo.openapi.models.LogoutReq
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val action: Action = Action.Initial,
    val message: String = "",

    val payload: TeacherTokenPayload = TeacherTokenPayload(),
) {
    enum class Action {
        Initial, GetAccount, Logout
    }
}

class TeacherHomeSettingViewModel(
    private val accountApi: AccountApi,
    private val tokenManager: TokenManager,

    private val authRepository: AuthRepository,
    private val resetRepository: ResetRepository,
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
            val payload = tokenPayloadManager.decodeTeacher(token.accessToken)
            _state.update { it.copy(payload = payload) }
        }
    }


    fun logout() = viewModelScope.launch {
        try {
            _state.update {
                it.copy(
                    status = StateStatus.Loading,
                    action = State.Action.Logout
                )
            }

            accountApi.logout(
                LogoutReq(
                    refreshToken = tokenManager.get().refreshToken
                )
            )
            tokenManager.clear()

            _state.update {
                it.copy(
                    status = StateStatus.Success,
                    action = State.Action.Logout
                )
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    action = State.Action.Logout,
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    action = State.Action.Logout,
                    message = "Terjadi kesalahan tak terduga!"
                )
            }
        }
    }
}