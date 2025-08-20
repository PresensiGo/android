package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.major

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.BatchApi
import com.rizalanggoro.presensigo.openapi.apis.MajorApi
import com.rizalanggoro.presensigo.openapi.models.GetAllMajorsByBatchIdRes
import com.rizalanggoro.presensigo.openapi.models.GetBatchRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MajorViewModel(
    savedStateHandle: SavedStateHandle,
    private val batchApi: BatchApi,
    private val majorApi: MajorApi
) : ViewModel() {
    private val _batchState = MutableStateFlow<UiState<GetBatchRes>>(UiState.Initial)
    val batchState get() = _batchState.asStateFlow()

    private val _majorsState = MutableStateFlow<UiState<GetAllMajorsByBatchIdRes>>(UiState.Initial)
    val majorsState get() = _majorsState.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.Subject.Major>()

    init {
        getBatch()
        getAllMajors()
    }

    fun getBatch() = viewModelScope.launch {
        try {
            _batchState.update { UiState.Loading }
            val body = batchApi.getBatch(batchId = params.batchId).body()
            _batchState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _batchState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _batchState.update { UiState.Failure() }
        }
    }

    fun getAllMajors() = viewModelScope.launch {
        try {
            _majorsState.update { UiState.Loading }
            val body = majorApi.getAllMajorsByBatchId(batchId = params.batchId).body()
            _majorsState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _batchState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _batchState.update { UiState.Failure() }
        }
    }
}