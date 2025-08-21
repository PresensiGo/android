package com.rizalanggoro.presensigo.presentation.pages.home.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.domain.QrData
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.CreateGeneralAttendanceRecordStudentReq
import com.rizalanggoro.presensigo.openapi.models.CreateSubjectAttendanceRecordStudentReq
import com.rizalanggoro.presensigo.openapi.models.GetAllGeneralAttendancesStudentRes
import com.rizalanggoro.presensigo.openapi.models.GetAllSubjectAttendancesStudentRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentHomeViewModel(
    private val attendanceApi: AttendanceApi,
) : ViewModel() {
    // subject attendance
    private val _subjectAttendances = MutableStateFlow<UiState<GetAllSubjectAttendancesStudentRes>>(
        UiState.Loading
    )
    val subjectAttendances get() = _subjectAttendances.asStateFlow()

    fun getSubjectAttendances() = viewModelScope.launch {
        try {
            _subjectAttendances.update { UiState.Loading }
            val body = attendanceApi.getAllSubjectAttendancesStudent().body()
            _subjectAttendances.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _subjectAttendances.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _subjectAttendances.update { UiState.Failure() }
        }
    }

    // general attendance
    private val _generalAttendances = MutableStateFlow<UiState<GetAllGeneralAttendancesStudentRes>>(
        UiState.Loading
    )
    val generalAttendances get() = _generalAttendances.asStateFlow()

    fun getGeneralAttendances() = viewModelScope.launch {
        try {
            _generalAttendances.update { UiState.Loading }
            val body = attendanceApi.getAllGeneralAttendancesStudent().body()
            _generalAttendances.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _generalAttendances.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _generalAttendances.update { UiState.Failure() }
        }
    }

    // process attendance
    private val _processAttendance = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val processAttendance get() = _processAttendance.asStateFlow()

    fun resetProcessAttendance() = _processAttendance.update { UiState.Initial }

    fun processAttendance(data: String) = viewModelScope.launch {
        try {
            _processAttendance.update { UiState.Loading }

            val qrData = Gson().fromJson(data, QrData::class.java)
            if (qrData.type == "general") {
                // general
                attendanceApi.createGeneralAttendanceRecordStudent(
                    CreateGeneralAttendanceRecordStudentReq(
                        code = qrData.code
                    )
                )
            } else {
                // subject
                attendanceApi.createSubjectAttendanceRecordStudent(
                    CreateSubjectAttendanceRecordStudentReq(
                        code = qrData.code
                    )
                )
            }

            _processAttendance.update { UiState.Success(Unit) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _processAttendance.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _processAttendance.update { UiState.Failure() }
        }
    }

    init {
        getSubjectAttendances()
        getGeneralAttendances()
    }
}