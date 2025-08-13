package com.rizalanggoro.presensigo.presentation.pages.home.teacher.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.BatchApi
import com.rizalanggoro.presensigo.openapi.models.Batch
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val batches: List<Batch> = emptyList(),
    val message: String = ""
)

class TeacherHomeSubjectAttendanceViewModel(
    private val batchApi: BatchApi
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    init {
        getAllBatches()
    }

    fun getAllBatches() = viewModelScope.launch {
        try {
            _state.update { it.copy(status = StateStatus.Loading) }

            val body = batchApi.getAllBatches().body()

            _state.update {
                it.copy(
                    status = StateStatus.Success,
                    batches = body.batches
                )
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    status = StateStatus.Failure,
                    message = "Terjadi kesalahan tak terduga!"
                )
            }
        }
    }
}