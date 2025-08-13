package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.apis.SubjectApi
import com.rizalanggoro.presensigo.openapi.models.CreateSubjectAttendanceReq
import com.rizalanggoro.presensigo.openapi.models.Subject
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

data class State(
    val status: StateStatus = StateStatus.Initial,
    val action: Action = Action.Initial,
    val message: String = "",

    val subjects: List<Subject> = emptyList()
) {
    enum class Action {
        Initial, Create, GetAllSubjects
    }
}

class CreateSubjectAttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val attendanceApi: AttendanceApi,
    private val subjectApi: SubjectApi
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.Subject.Create>()

    init {
        getAllSubjects()
    }

    fun getAllSubjects() = viewModelScope.launch {
        try {
            _state.update {
                it.copy(
                    status = StateStatus.Loading,
                    action = State.Action.GetAllSubjects
                )
            }

            val body = subjectApi.getAllSubjects().body()

            _state.update {
                it.copy(
                    status = StateStatus.Success,
                    action = State.Action.GetAllSubjects,
                    subjects = body.subjects
                )
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    action = State.Action.GetAllSubjects,
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    action = State.Action.GetAllSubjects,
                    message = "Terjadi kesalahan tak terduga!"
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun create(subject: Int, date: Long, time: TimePickerState, note: String) =
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        status = StateStatus.Loading,
                        action = State.Action.Create
                    )
                }

                val dateStr = date.formatDateTime("yyyy-MM-dd")
                val timeStr = String.format(
                    Locale("id", "ID"), "%02d:%02d:00",
                    time.hour, time.minute
                )
                val datetime = "$dateStr $timeStr"
                attendanceApi.createSubjectAttendance(
                    batchId = params.batchId,
                    majorId = params.majorId,
                    classroomId = params.classroomId,
                    body = CreateSubjectAttendanceReq(
                        datetime = datetime,
                        note = note,
                        subjectId = subject
                    )
                )

                _state.update {
                    it.copy(
                        status = StateStatus.Success,
                        action = State.Action.Create
                    )
                }
            } catch (e: ResponseException) {
                e.printStackTrace()
                _state.update {
                    it.copy(
                        status = StateStatus.Failure,
                        action = State.Action.Create,
                        message = e.response.bodyAsText().toFailure().message
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.update {
                    it.copy(
                        status = StateStatus.Failure,
                        action = State.Action.Create,
                        message = "Terjadi kesalahan tak terduga!"
                    )
                }
            }
        }
}