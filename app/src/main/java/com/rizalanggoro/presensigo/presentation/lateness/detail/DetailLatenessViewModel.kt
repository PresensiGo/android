package com.rizalanggoro.presensigo.presentation.lateness.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.data.repositories.LatenessRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial
)

class DetailLatenessViewModel(
    savedStateHandle: SavedStateHandle,
    private val latenessRepository: LatenessRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Lateness.Detail.Index>()

    init {
        getLateness()
    }

    fun getLateness() = viewModelScope.launch { }
}