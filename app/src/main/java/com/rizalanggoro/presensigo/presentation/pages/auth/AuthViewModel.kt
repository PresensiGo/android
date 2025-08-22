package com.rizalanggoro.presensigo.presentation.pages.auth

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.domain.Token
import com.rizalanggoro.presensigo.domain.TokenType
import com.rizalanggoro.presensigo.openapi.apis.AccountApi
import com.rizalanggoro.presensigo.openapi.apis.StudentApi
import com.rizalanggoro.presensigo.openapi.models.LoginReq
import com.rizalanggoro.presensigo.openapi.models.LoginStudentReq
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val application: Application,
    private val accountApi: AccountApi,
    private val studentApi: StudentApi,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _loginState = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val loginState = _loginState.asStateFlow()

    fun resetLoginState() = _loginState.update { UiState.Initial }

    @SuppressLint("HardwareIds")
    fun loginStudent(nis: String, schoolCode: String) = viewModelScope.launch {
        try {
            _loginState.update { UiState.Loading }

            val androidId = Settings.Secure.getString(
                application.contentResolver,
                Settings.Secure.ANDROID_ID
            )
            val body = studentApi.loginStudent(
                LoginStudentReq(
                    deviceId = androidId,
                    nis = nis,
                    schoolCode = schoolCode
                )
            ).body()

            tokenManager.set(
                Token(
                    tokenType = TokenType.Student,
                    accessToken = body.accessToken,
                    refreshToken = body.refreshToken
                )
            )

            _loginState.update { UiState.Success(Unit) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _loginState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _loginState.update { UiState.Failure() }
        }
    }

    fun loginTeacher(email: String, password: String) = viewModelScope.launch {
        try {
            _loginState.update { UiState.Loading }

            val body = accountApi.login(
                LoginReq(
                    email = email,
                    password = password
                )
            ).body()

            tokenManager.set(
                Token(
                    tokenType = TokenType.Teacher,
                    accessToken = body.accessToken,
                    refreshToken = body.refreshToken
                )
            )

            _loginState.update { UiState.Success(Unit) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _loginState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _loginState.update { UiState.Failure() }
        }
    }
}