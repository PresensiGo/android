package com.rizalanggoro.presensigo.presentation.pages.home.student

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.domain.QrData
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.CreateGeneralAttendanceRecordStudentReq
import com.rizalanggoro.presensigo.openapi.models.CreateSubjectAttendanceRecordStudentReq
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial
)

class StudentHomeViewModel(
    savedStateHandle: SavedStateHandle,
    private val attendanceApi: AttendanceApi,
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    fun processAttendance(data: String) = viewModelScope.launch {
        try {
            _state.update { it.copy(status = StateStatus.Loading) }

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

            _state.update { it.copy(status = StateStatus.Success) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _state.update { it.copy(status = StateStatus.Failure) }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update { it.copy(status = StateStatus.Failure) }
        }
    }
}