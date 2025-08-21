package com.rizalanggoro.presensigo.presentation.pages.home.teacher.general

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.GetAllGeneralAttendancesRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeacherHomeGeneralAttendanceViewModel(
    private val attendanceApi: AttendanceApi
) : ViewModel() {
    private val _attendances = MutableStateFlow<UiState<GetAllGeneralAttendancesRes>>(
        UiState.Loading
    )
    val attendances = _attendances.asStateFlow()
    fun getAllGeneralAttendances() = viewModelScope.launch {
        try {
            _attendances.update { UiState.Loading }

            val body = attendanceApi.getAllGeneralAttendances().body()

            _attendances.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _attendances.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _attendances.update { UiState.Failure() }
        }
    }

    init {
        getAllGeneralAttendances()
    }
}