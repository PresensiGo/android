package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail

import android.graphics.Bitmap
import android.icu.util.Calendar
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.AppAttendanceStatus
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.toConstantsAttendanceStatus
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.core.qr.QrGenerator
import com.rizalanggoro.presensigo.domain.QrData
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.apis.ClassroomApi
import com.rizalanggoro.presensigo.openapi.apis.SubjectApi
import com.rizalanggoro.presensigo.openapi.models.ConstantsAttendanceStatus
import com.rizalanggoro.presensigo.openapi.models.CreateSubjectAttendanceRecordReq
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
    val sickItems: List<GetAllSubjectAttendanceRecordsItem> = emptyList(),
    val permissionItems: List<GetAllSubjectAttendanceRecordsItem> = emptyList(),
    val alphaItems: List<GetAllSubjectAttendanceRecordsItem> = emptyList(),
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

    private val _createRecordState = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val createRecordState get() = _createRecordState.asStateFlow()

    private val _deleteRecordState = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val deleteRecordState get() = _deleteRecordState.asStateFlow()

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

            val hasRecordItems = body.items.filter { it.record.id > 0 }

            _recordsState.update {
                UiState.Success(
                    data = RecordsState(
                        presentItems = hasRecordItems.filter {
                            it.record.status == ConstantsAttendanceStatus.AttendanceStatusPresent
                        },
                        sickItems = hasRecordItems.filter {
                            it.record.status == ConstantsAttendanceStatus.AttendanceStatusSick
                        },
                        permissionItems = hasRecordItems.filter {
                            it.record.status == ConstantsAttendanceStatus.AttendanceStatusPermission
                        },
                        alphaItems = body.items.filter {
                            it.record.id == 0 || it.record.status == ConstantsAttendanceStatus.AttendanceStatusAlpha
                        }
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

    fun createRecord(studentId: Int, status: AppAttendanceStatus) = viewModelScope.launch {
        try {
            if (attendanceState.value is UiState.Success) {
                val attendance = (attendanceState.value as UiState.Success).data.subjectAttendance

                _createRecordState.update { UiState.Loading }
                attendanceApi.createSubjectAttendanceRecord(
                    batchId = params.batchId,
                    majorId = params.majorId,
                    classroomId = params.classroomId,
                    subjectAttendanceId = params.attendanceId,
                    body = CreateSubjectAttendanceRecordReq(
                        datetime = when (status) {
                            AppAttendanceStatus.Present -> attendance.dateTime.formatDateTime("yyyy-MM-dd HH:mm:ss")
                            else -> Calendar.getInstance().timeInMillis.formatDateTime("yyyy-MM-dd HH:mm:ss")
                        },
                        status = status.toConstantsAttendanceStatus(),
                        studentId = studentId
                    )
                )
                _createRecordState.update { UiState.Success(Unit) }
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _createRecordState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _createRecordState.update { UiState.Failure() }
        }
    }

    fun deleteRecord(recordId: Int) = viewModelScope.launch {
        try {
            _deleteRecordState.update { UiState.Loading }
            attendanceApi.deleteSubjectAttendanceRecord(
                batchId = params.batchId,
                majorId = params.majorId,
                classroomId = params.classroomId,
                subjectAttendanceId = params.attendanceId,
                recordId = recordId
            )
            _deleteRecordState.update { UiState.Success(Unit) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _deleteRecordState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _deleteRecordState.update { UiState.Failure() }
        }
    }

    fun resetCreateRecordState() = _createRecordState.update { UiState.Initial }
    fun resetDeleteRecordState() = _deleteRecordState.update { UiState.Initial }
}