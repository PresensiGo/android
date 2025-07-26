package com.rizalanggoro.presensigo.presentation.attendance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.AttendanceRepository
import com.rizalanggoro.presensigo.domain.Attendance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val action: Action = Action.Initial,
    val attendances: List<Attendance> = emptyList()
) {
    enum class Action {
        Initial, GetAll, Delete
    }
}

class AttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    private val params = savedStateHandle.toRoute<Routes.Attendance.List>()

    init {
        getAllAttendances()
    }

    fun resetState() = _state.update { it.copy(status = StateStatus.Initial) }

    fun getAllAttendances() = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.GetAll
            )
        }
        attendanceRepository.getAll(params.classroomID)
            .onLeft { result ->
                _state.update {
                    it.copy(
                        status = StateStatus.Success,
                        attendances = result
                    )
                }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }

    fun deleteAttendance(attendance: Attendance) = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.Delete
            )
        }
        attendanceRepository.delete(attendanceID = attendance.id)
            .onLeft {
                _state.update { it.copy(status = StateStatus.Success) }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }
}