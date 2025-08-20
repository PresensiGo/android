package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.core.qr.QrGenerator
import com.rizalanggoro.presensigo.domain.QrData
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.apis.ClassroomApi
import com.rizalanggoro.presensigo.openapi.apis.SubjectApi
import com.rizalanggoro.presensigo.openapi.models.GetAllSubjectAttendanceRecordsItem
import com.rizalanggoro.presensigo.openapi.models.GetClassroomRes
import com.rizalanggoro.presensigo.openapi.models.GetSubjectAttendanceRes
import com.rizalanggoro.presensigo.openapi.models.GetSubjectRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RecordsState(
    val presentItems: List<GetAllSubjectAttendanceRecordsItem> = emptyList(),
    val notYetItems: List<GetAllSubjectAttendanceRecordsItem> = emptyList(),
)

class DetailSubjectAttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val attendanceApi: AttendanceApi,
    private val subjectApi: SubjectApi,
    private val classroomApi: ClassroomApi
) : ViewModel() {
    private val _qrCode = MutableStateFlow<Bitmap?>(null)
    val qrCode get() = _qrCode.asStateFlow()

    private val _attendanceState = MutableStateFlow<UiState<GetSubjectAttendanceRes>>(
        UiState.Loading
    )
    val attendanceState get() = _attendanceState.asStateFlow()

    private val _subjectState = MutableStateFlow<UiState<GetSubjectRes>>(UiState.Loading)
    val subjectState get() = _subjectState.asStateFlow()

    private val _classroomState = MutableStateFlow<UiState<GetClassroomRes>>(UiState.Loading)
    val classroomState get() = _classroomState.asStateFlow()

    private val _recordsState = MutableStateFlow<UiState<RecordsState>>(UiState.Loading)
    val recordsState get() = _recordsState.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.Subject.Detail>()

    init {
        getAttendance()
        getAllRecords()
    }

    fun getAttendance() = viewModelScope.launch {
        try {
            _attendanceState.update { UiState.Loading }
            val body = attendanceApi.getSubjectAttendance(
                batchId = params.batchId,
                majorId = params.majorId,
                classroomId = params.classroomId,
                subjectAttendanceId = params.attendanceId
            ).body()

            getSubject(subjectId = body.subjectAttendance.subjectId)
            getClassroom()

            // generate qrcode
            val qrCodeData = Gson().toJson(
                QrData(
                    type = "subject",
                    code = body.subjectAttendance.code
                )
            )

            _qrCode.update { QrGenerator.generateBitmap(qrCodeData) }
            _attendanceState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _attendanceState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _attendanceState.update { UiState.Failure() }
        }
    }

    fun getSubject(subjectId: Int) = viewModelScope.launch {
        try {
            _subjectState.update { UiState.Loading }
            val body = subjectApi.getSubject(subjectId = subjectId).body()
            _subjectState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _subjectState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _subjectState.update { UiState.Failure() }
        }
    }

    fun getClassroom() = viewModelScope.launch {
        try {
            _classroomState.update { UiState.Loading }
            val body = classroomApi.getClassroom(
                batchId = params.batchId,
                majorId = params.majorId,
                classroomId = params.classroomId
            ).body()
            _classroomState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _classroomState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _classroomState.update { UiState.Failure() }
        }
    }

    fun getAllRecords() = viewModelScope.launch {
        try {
            _recordsState.update { UiState.Loading }

            val body = attendanceApi.getAllSubjectAttendanceRecords(
                batchId = params.batchId,
                majorId = params.majorId,
                classroomId = params.classroomId,
                subjectAttendanceId = params.attendanceId
            ).body()

            _recordsState.update {
                UiState.Success(
                    data = RecordsState(
                        presentItems = body.items.filter { it.record.id > 0 },
                        notYetItems = body.items.filter { it.record.id == 0 }
                    )
                )
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _recordsState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _recordsState.update { UiState.Failure() }
        }
    }
}