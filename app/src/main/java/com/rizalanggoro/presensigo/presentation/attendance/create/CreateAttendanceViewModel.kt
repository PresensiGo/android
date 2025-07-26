package com.rizalanggoro.presensigo.presentation.attendance.create

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.AttendanceRepository
import com.rizalanggoro.presensigo.data.repositories.StudentRepository
import com.rizalanggoro.presensigo.domain.Attendance
import com.rizalanggoro.presensigo.domain.AttendanceDetail
import com.rizalanggoro.presensigo.domain.AttendanceStatus
import com.rizalanggoro.presensigo.domain.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val action: Action = Action.Initial,
    val students: List<StudentItem> = emptyList(),
    val selectedFilter: AttendanceStatus = AttendanceStatus.AttendanceAlpha
) {
    enum class Action {
        Initial, GetAll, Create
    }

    data class StudentItem(
        val student: Student = Student(),
        val status: AttendanceStatus = AttendanceStatus.AttendanceAlpha,
        val isSelected: Boolean = false
    )
}

class CreateAttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository,
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {
    companion object {
        private const val TAG = "CreateAttendanceViewModel"
    }

    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    private val params = savedStateHandle.toRoute<Routes.Attendance.Create>()

    init {
        getAllStudents()
    }

    fun getAllStudents() = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.GetAll
            )
        }
        studentRepository.getAll(params.classroomID)
            .onLeft { result ->
                _state.update {
                    it.copy(
                        status = StateStatus.Success,
                        students = result.map {
                            State.StudentItem(student = it)
                        }
                    )
                }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }

    fun changeFilter(filter: AttendanceStatus) = _state.update {
        it.copy(selectedFilter = filter)
    }

    fun changeSelected(student: Student, isSelected: Boolean) = _state.update {
        it.copy(students = it.students.map { item ->
            when (item.student.id == student.id) {
                true -> item.copy(isSelected = isSelected)
                else -> item
            }
        })
    }

    fun updateStatusBatch(status: AttendanceStatus) = _state.update {
        it.copy(
            students = it.students.map {
                when (it.isSelected) {
                    true -> it.copy(
                        status = status,
                        isSelected = false
                    )

                    else -> it
                }
            }
        )
    }

    fun create() = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.Create
            )
        }
        attendanceRepository.create(
            attendance = Attendance(
                classroomId = params.classroomID,
                date = "2025-07-25",
            ),
            attendanceDetails = state.value.students.map {
                AttendanceDetail(
                    studentID = it.student.id,
                    status = it.status,
                    note = "no note"
                )
            }
        )
            .onLeft {
                _state.update { it.copy(status = StateStatus.Success) }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
                Log.d(TAG, "create: $it")
            }
    }
}