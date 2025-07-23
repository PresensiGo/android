package com.rizalanggoro.presensigo.presentation.classroom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.ClassroomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClassroomViewModel(
    savedStateHandle: SavedStateHandle,
    private val classroomRepository: ClassroomRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ClassroomState())
    val uiState get() = _uiState.asStateFlow()

    private val params = savedStateHandle.toRoute<Routes.Classroom>()

    init {
        getAllClassrooms()
    }

    fun getAllClassrooms() = viewModelScope.launch {
        _uiState.update { it.copy(status = StateStatus.Loading) }
        classroomRepository.getAll(params.majorId)
            .onLeft { result ->
                _uiState.update {
                    it.copy(
                        status = StateStatus.Success,
                        classrooms = result
                    )
                }
            }
            .onRight {
                _uiState.update { it.copy(status = StateStatus.Failure) }
            }
    }
}