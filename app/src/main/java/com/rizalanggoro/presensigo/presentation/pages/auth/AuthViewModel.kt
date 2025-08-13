package com.rizalanggoro.presensigo.presentation.pages.auth

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.domain.Token
import com.rizalanggoro.presensigo.domain.TokenType
import com.rizalanggoro.presensigo.openapi.apis.AuthApi
import com.rizalanggoro.presensigo.openapi.apis.StudentApi
import com.rizalanggoro.presensigo.openapi.models.LoginReq
import com.rizalanggoro.presensigo.openapi.models.LoginStudentReq
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val message: String = ""
)

class AuthViewModel(
    private val application: Application,
    private val authApi: AuthApi,
    private val studentApi: StudentApi,
    private val tokenManager: TokenManager
) : ViewModel() {
    companion object {
        const val TAG = "AuthViewModel"
    }

    private var _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    @SuppressLint("HardwareIds")
    fun loginStudent(nis: String, schoolCode: String) = viewModelScope.launch {
        try {
            _state.update { it.copy(status = StateStatus.Loading) }

            val androidId = Settings.Secure.getString(
                application.contentResolver,
                Settings.Secure.ANDROID_ID
            )
            val response = studentApi.loginStudent(
                LoginStudentReq(
                    deviceId = androidId,
                    nis = nis,
                    schoolCode = schoolCode
                )
            )

            val body = response.body()
            tokenManager.set(
                Token(
                    tokenType = TokenType.Student,
                    accessToken = body.accessToken,
                    refreshToken = body.refreshToken
                )
            )

            _state.update { it.copy(status = StateStatus.Success) }
        } catch (e: ResponseException) {
            e.printStackTrace()

            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()

            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    message = "Terjadi kesalahan tak terduga!"
                )
            }
        }
    }

    fun loginTeacher(email: String, password: String) = viewModelScope.launch {
        try {
            _state.update { it.copy(status = StateStatus.Loading) }

            val response = authApi.login(LoginReq(email = email, password = password))
            val body = response.body()

            tokenManager.set(
                Token(
                    tokenType = TokenType.Teacher,
                    accessToken = body.accessToken,
                    refreshToken = body.refreshToken
                )
            )

            _state.update { it.copy(status = StateStatus.Success) }
        } catch (e: ResponseException) {
            e.printStackTrace()

            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()

            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    message = "Terjadi kesalahan tak terduga!"
                )
            }
        }
    }
}