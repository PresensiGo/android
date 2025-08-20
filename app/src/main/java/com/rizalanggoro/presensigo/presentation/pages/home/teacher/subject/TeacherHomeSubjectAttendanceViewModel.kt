package com.rizalanggoro.presensigo.presentation.pages.home.teacher.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.BatchApi
import com.rizalanggoro.presensigo.openapi.models.GetAllBatchesRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeacherHomeSubjectAttendanceViewModel(
    private val batchApi: BatchApi
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<GetAllBatchesRes>>(
        UiState.Initial
    )
    val state get() = _state.asStateFlow()

    init {
        getAllBatches()
    }

    fun getAllBatches() = viewModelScope.launch {
        try {
            _state.update { UiState.Loading }
            val body = batchApi.getAllBatches().body()
            _state.update { UiState.Success(body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _state.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update { UiState.Failure() }
        }
    }
}