package com.rizalanggoro.presensigo.presentation.pages.home.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.BatchRepository
import com.rizalanggoro.presensigo.domain.combined.BatchInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val batches: List<BatchInfo> = emptyList()
)

class HomeAttendanceViewModel(
    private val batchRepository: BatchRepository
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    init {
        getAllBatches()
    }

    private fun getAllBatches() = viewModelScope.launch {
        _state.update { it.copy(status = StateStatus.Loading) }
        batchRepository.getAll()
            .onLeft { result ->
                _state.update {
                    it.copy(
                        status = StateStatus.Success,
                        batches = result
                    )
                }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }
}