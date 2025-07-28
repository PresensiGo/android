package com.rizalanggoro.presensigo.presentation.home.lateness

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.extensions.toApiFormatString
import com.rizalanggoro.presensigo.data.repositories.LatenessRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val action: Action = State.Action.Initial
) {
    enum class Action {
        Initial, Create, GetAll
    }
}

class HomeLatenessViewModel(
    private val latenessRepository: LatenessRepository
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    init {
        getAllLatenesses()
    }

    fun resetState() = _state.update {
        it.copy(
            status = StateStatus.Initial,
            action = State.Action.Initial
        )
    }

    fun getAllLatenesses() = viewModelScope.launch {
//        _state.update {
//            it.copy(
//                status = StateStatus.Loading,
//                action = State.Action.GetAll
//            )
//        }
    }

    fun create(date: Long) = viewModelScope.launch {
        _state.update {
            it.copy(
                status = StateStatus.Loading,
                action = State.Action.Create
            )
        }
        latenessRepository.create(date.toApiFormatString())
            .onLeft {
                _state.update { it.copy(status = StateStatus.Success) }
            }
            .onRight {
                _state.update { it.copy(status = StateStatus.Failure) }
            }
    }
}