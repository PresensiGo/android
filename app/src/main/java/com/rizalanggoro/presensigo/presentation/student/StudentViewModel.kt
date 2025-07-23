package com.rizalanggoro.presensigo.presentation.student

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(StudentState())
    val uiState get() = _uiState.asStateFlow()

    private val params = savedStateHandle.toRoute<Routes.Student>()

    init {
        getAllStudents()
    }

    fun getAllStudents() = viewModelScope.launch {
        _uiState.update { it.copy(status = StateStatus.Loading) }
        studentRepository.getAll(params.classroomId)
            .onLeft { result ->
                _uiState.update {
                    it.copy(
                        status = StateStatus.Success,
                        students = result
                    )
                }
            }
            .onRight {
                _uiState.update { it.copy(status = StateStatus.Failure) }
            }
    }
}