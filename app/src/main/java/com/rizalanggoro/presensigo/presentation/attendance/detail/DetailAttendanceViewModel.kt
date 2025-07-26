package com.rizalanggoro.presensigo.presentation.attendance.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.AttendanceRepository
import com.rizalanggoro.presensigo.domain.AttendanceStatus
import com.rizalanggoro.presensigo.domain.AttendanceWithDetail
import com.rizalanggoro.presensigo.domain.StudentWithAttendanceDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val action: Action = Action.Initial,
    val selectedFilter: AttendanceStatus = AttendanceStatus.AttendancePresent,
    val attendance: AttendanceWithDetail = AttendanceWithDetail()
) {
    enum class Action {
        Initial, GetAll
    }
}

class DetailAttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    val filteredItems: StateFlow<List<StudentWithAttendanceDetail>> = _state
        .map { state ->
            state.attendance.items.filter { item ->
                item.attendanceDetail.status == state.selectedFilter
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    val params = savedStateHandle.toRoute<Routes.Attendance.Detail>()

    init {
        getAllStudentAttendances()
    }

    fun resetState() = _state.update {
        it.copy(
            status = StateStatus.Initial,
            action = State.Action.Initial
        )
    }

    fun changeFilter(attendanceStatus: AttendanceStatus) = _state.update {
        it.copy(selectedFilter = attendanceStatus)
    }

    fun getAllStudentAttendances() = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.GetAll
            )
        }
        attendanceRepository.get(attendanceId = params.attendanceId)
            .onLeft { result ->
                _state.update {
                    it.copy(
                        status = StateStatus.Success,
                        attendance = result
                    )
                }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }
}