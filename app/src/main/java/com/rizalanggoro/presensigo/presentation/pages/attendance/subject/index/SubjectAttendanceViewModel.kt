package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.index

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.apis.BatchApi
import com.rizalanggoro.presensigo.openapi.apis.ClassroomApi
import com.rizalanggoro.presensigo.openapi.apis.MajorApi
import com.rizalanggoro.presensigo.openapi.models.GetAllSubjectAttendancesItem
import com.rizalanggoro.presensigo.openapi.models.GetAllSubjectAttendancesRes
import com.rizalanggoro.presensigo.openapi.models.GetBatchRes
import com.rizalanggoro.presensigo.openapi.models.GetClassroomRes
import com.rizalanggoro.presensigo.openapi.models.GetMajorRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val items: List<GetAllSubjectAttendancesItem> = emptyList(),
    val message: String = ""
)

class SubjectAttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val batchApi: BatchApi,
    private val majorApi: MajorApi,
    private val classroomApi: ClassroomApi,
    private val attendanceApi: AttendanceApi
) : ViewModel() {
    private val _batchState = MutableStateFlow<UiState<GetBatchRes>>(UiState.Initial)
    val batchState get() = _batchState.asStateFlow()

    private val _majorState = MutableStateFlow<UiState<GetMajorRes>>(UiState.Initial)
    val majorState get() = _majorState.asStateFlow()

    private val _classroomState = MutableStateFlow<UiState<GetClassroomRes>>(UiState.Initial)
    val classroomState get() = _classroomState.asStateFlow()

    private val _attendancesState = MutableStateFlow<UiState<GetAllSubjectAttendancesRes>>(
        UiState.Initial
    )
    val attendancesState get() = _attendancesState.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.Subject.Index>()

    init {
        getBatch()
        getMajor()
        getClassroom()
        getAllAttendances()
    }

    fun getBatch() = viewModelScope.launch {
        try {
            _batchState.update { UiState.Loading }
            val body = batchApi.getBatch(batchId = params.batchId).body()
            _batchState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _batchState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _batchState.update { UiState.Failure() }
        }
    }

    fun getMajor() = viewModelScope.launch {
        try {
            _majorState.update { UiState.Loading }
            val body = majorApi.getMajor(
                batchId = params.batchId,
                majorId = params.majorId
            ).body()
            _majorState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _majorState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _majorState.update { UiState.Failure() }
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

    fun getAllAttendances() = viewModelScope.launch {
        try {
            _attendancesState.update { UiState.Loading }

            val body = attendanceApi.getAllSubjectAttendances(
                batchId = params.batchId,
                majorId = params.majorId,
                classroomId = params.classroomId
            ).body()

            _attendancesState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _attendancesState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _attendancesState.update { UiState.Failure() }
        }
    }
}