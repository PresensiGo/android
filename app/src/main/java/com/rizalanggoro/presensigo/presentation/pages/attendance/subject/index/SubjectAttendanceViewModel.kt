package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.index

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.GetAllSubjectAttendancesItem
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
    private val attendanceApi: AttendanceApi
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.Subject.Index>()

    init {
        getAllAttendances()
    }

    fun getAllAttendances() = viewModelScope.launch {
        try {
            _state.update { it.copy(status = StateStatus.Loading) }

            val body = attendanceApi.getAllSubjectAttendances(
                batchId = params.batchId,
                majorId = params.majorId,
                classroomId = params.classroomId
            ).body()

            _state.update {
                it.copy(
                    status = StateStatus.Success,
                    items = body.items
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