package com.rizalanggoro.presensigo.presentation.pages.home.teacher.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.openapi.apis.AccountApi
import com.rizalanggoro.presensigo.openapi.apis.SchoolApi
import com.rizalanggoro.presensigo.openapi.models.GetAccountRes
import com.rizalanggoro.presensigo.openapi.models.GetSchoolRes
import com.rizalanggoro.presensigo.openapi.models.LogoutReq
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeacherHomeSettingViewModel(
    private val accountApi: AccountApi,
    private val schoolApi: SchoolApi,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _account = MutableStateFlow<UiState<GetAccountRes>>(UiState.Loading)
    val account get() = _account.asStateFlow()

    fun getAccount() = viewModelScope.launch {
        try {
            _account.update { UiState.Loading }

            val body = accountApi.getAccount().body()

            _account.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _account.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _account.update { UiState.Failure() }
        }
    }

    private val _school = MutableStateFlow<UiState<GetSchoolRes>>(UiState.Loading)
    val school get() = _school.asStateFlow()

    fun getSchool() = viewModelScope.launch {
        try {
            _school.update { UiState.Loading }

            val body = schoolApi.getSchool().body()

            _school.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _school.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _school.update { UiState.Failure() }
        }
    }

    init {
        getAccount()
        getSchool()
    }

    // logout
    private val _logout = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val logout get() = _logout.asStateFlow()

    fun logout() = viewModelScope.launch {
        try {
            _logout.update { UiState.Loading }

            val token = tokenManager.get()
            accountApi.logout(
                body = LogoutReq(
                    refreshToken = token.refreshToken
                )
            )

            _logout.update { UiState.Success(Unit) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _logout.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _logout.update { UiState.Failure() }
        }
    }
}