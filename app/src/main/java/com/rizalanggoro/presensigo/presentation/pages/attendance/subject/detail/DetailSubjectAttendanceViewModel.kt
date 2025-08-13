package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.core.qr.QrGenerator
import com.rizalanggoro.presensigo.domain.QrData
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.SubjectAttendance
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val attendance: SubjectAttendance? = null,
    val message: String = "",

    val qrCodeBitmap: Bitmap? = null
)

class DetailSubjectAttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val attendanceApi: AttendanceApi
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.Subject.Detail>()

    init {
        getSubjectAttendance()
    }

    fun getSubjectAttendance() = viewModelScope.launch {
        try {
            _state.update { it.copy(status = StateStatus.Loading) }

            val body = attendanceApi.getSubjectAttendance(
                batchId = params.batchId,
                majorId = params.majorId,
                classroomId = params.classroomId,
                subjectAttendanceId = params.attendanceId
            ).body()

            // generate qrcode
            val qrCodeData = Gson().toJson(
                QrData(
                    type = "subject",
                    code = body.subjectAttendance.code
                )
            )

            _state.update {
                it.copy(
                    status = StateStatus.Success,
                    attendance = body.subjectAttendance,
                    qrCodeBitmap = QrGenerator.generateBitmap(qrCodeData)
                )
            }
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