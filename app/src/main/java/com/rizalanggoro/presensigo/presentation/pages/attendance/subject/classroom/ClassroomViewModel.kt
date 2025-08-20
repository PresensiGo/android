package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.classroom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.BatchApi
import com.rizalanggoro.presensigo.openapi.apis.ClassroomApi
import com.rizalanggoro.presensigo.openapi.apis.MajorApi
import com.rizalanggoro.presensigo.openapi.models.GetAllClassroomsByMajorIdRes
import com.rizalanggoro.presensigo.openapi.models.GetBatchRes
import com.rizalanggoro.presensigo.openapi.models.GetMajorRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClassroomViewModel(
    savedStateHandle: SavedStateHandle,
    private val batchApi: BatchApi,
    private val majorApi: MajorApi,
    private val classroomApi: ClassroomApi
) : ViewModel() {
    private val _batchState = MutableStateFlow<UiState<GetBatchRes>>(UiState.Initial)
    val batchState get() = _batchState.asStateFlow()

    private val _majorState = MutableStateFlow<UiState<GetMajorRes>>(UiState.Initial)
    val majorState get() = _majorState.asStateFlow()

    private val _classroomsState = MutableStateFlow<UiState<GetAllClassroomsByMajorIdRes>>(
        UiState.Initial
    )
    val classroomsState get() = _classroomsState.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.Subject.Classroom>()

    init {
        getBatch()
        getMajor()
        getAllClassrooms()
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

    fun getMajor() = viewModelScope.launch {
        try {
            _majorState.update { UiState.Loading }
            val body = majorApi.getMajor(
                batchId = params.batchId,
                majorId = params.majorId
            ).body()
            _majorState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _majorState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _majorState.update { UiState.Failure() }
        }
    }

    fun getAllClassrooms() = viewModelScope.launch {
        try {
            _classroomsState.update { UiState.Loading }

            val body = classroomApi.getAllClassroomsByMajorId(
                batchId = params.batchId,
                majorId = params.majorId
            ).body()

            _classroomsState.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _classroomsState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _classroomsState.update { UiState.Failure() }
        }
    }
}