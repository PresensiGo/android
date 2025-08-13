package com.rizalanggoro.presensigo.presentation.lateness.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.LatenessRepository
import com.rizalanggoro.presensigo.domain.Lateness
import com.rizalanggoro.presensigo.domain.combined.StudentMajorClassroom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val lateness: Lateness = Lateness(),
    val items: List<StudentMajorClassroom> = emptyList()
)

class DetailLatenessViewModel(
    savedStateHandle: SavedStateHandle,
    private val latenessRepository: LatenessRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

//    val params = savedStateHandle.toRoute<Routes.Lateness.Detail.Index>()

    init {
        getLateness()
    }

    fun getLateness() = viewModelScope.launch {
        _state.update { it.copy(status = StateStatus.Loading) }
//        latenessRepository.getDetail(latenessId = params.latenessId)
//            .onLeft { result ->
//                _state.update {
//                    it.copy(
//                        status = StateStatus.Success,
//                        lateness = result.lateness,
//                        items = result.items
//                    )
//                }
//            }
//            .onRight {
//                _state.update { it.copy(status = StateStatus.Failure) }
//            }
    }
}