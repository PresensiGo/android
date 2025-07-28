package com.rizalanggoro.presensigo.presentation.lateness.detail.create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.LatenessRepository
import com.rizalanggoro.presensigo.data.repositories.StudentRepository
import com.rizalanggoro.presensigo.domain.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val action: Action = Action.Initial,
    val students: List<Student> = emptyList()
) {
    enum class Action {
        Initial, FindStudents, Create
    }
}

class CreateDetailLatenessViewModel(
    savedStateHandle: SavedStateHandle,
    private val latenessRepository: LatenessRepository,
    private val studentRepository: StudentRepository
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Lateness.Detail.Create>()

    fun resetState() = _state.update {
        it.copy(
            status = StateStatus.Initial,
            action = State.Action.Initial
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
}