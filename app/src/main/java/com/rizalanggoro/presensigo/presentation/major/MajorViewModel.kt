package com.rizalanggoro.presensigo.presentation.major

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.MajorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MajorViewModel(
    savedStateHandle: SavedStateHandle,
    private val majorRepository: MajorRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MajorState())
    val uiState get() = _uiState.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Major>()

    init {
        getAllMajors()
    }

    fun getAllMajors() = viewModelScope.launch {
        _uiState.update { it.copy(status = StateStatus.Loading) }
        majorRepository.getAll(params.batchId)
            .onLeft { result ->
                _uiState.update {
                    it.copy(
                        status = StateStatus.Success,
                        majors = result
                    )
                }
            }
            .onRight {
                _uiState.update {
                    it.copy(status = StateStatus.Failure)
                }
            }
    }
}