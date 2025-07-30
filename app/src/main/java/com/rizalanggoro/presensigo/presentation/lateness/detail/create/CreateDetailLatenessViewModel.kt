package com.rizalanggoro.presensigo.presentation.lateness.detail.create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.LatenessRepository
import com.rizalanggoro.presensigo.data.repositories.StudentRepository
import com.rizalanggoro.presensigo.domain.combined.StudentMajorClassroom
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
    val students: List<StudentMajorClassroom> = emptyList(),
    val selectedStudentIds: List<Int> = emptyList()
) {
    enum class Action {
        Initial, FindStudents, Create
    }

    data class Item(
        val isSelected: Boolean,
        val data: StudentMajorClassroom
    )
}

class CreateDetailLatenessViewModel(
    savedStateHandle: SavedStateHandle,
    private val latenessRepository: LatenessRepository,
    private val studentRepository: StudentRepository
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    val studentsWithSelectionState: StateFlow<List<State.Item>> = _state
        .map { state ->
            state.students.map {
                State.Item(
                    isSelected = state.selectedStudentIds.contains(it.student.id),
                    data = it
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    val params = savedStateHandle.toRoute<Routes.Lateness.Detail.Create>()

    fun resetState() = _state.update {
        it.copy(
            status = StateStatus.Initial,
            action = State.Action.Initial
        )
    }

    fun selectStudent(studentId: Int, isSelected: Boolean) = _state.update {
        it.copy(
            selectedStudentIds = when (isSelected) {
                true -> it.selectedStudentIds.plus(studentId)
                else -> it.selectedStudentIds.minus(studentId)
            }
        )
    }

    fun searchStudents(keyword: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.FindStudents
            )
        }
        studentRepository.getAll(keyword = keyword)
            .onLeft { result ->
                _state.update {
                    it.copy(
                        status = StateStatus.Success,
                        students = result
                    )
                }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }

    fun create() = viewModelScope.launch {
        _state.update {
            it.copy(
                action = State.Action.Create,
                status = StateStatus.Loading
            )
        }
        latenessRepository.createDetail(
            latenessId = params.latenessId,
            studentIds = state.value.selectedStudentIds,
        )
            .onLeft {
                _state.update { it.copy(status = StateStatus.Success) }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }
}