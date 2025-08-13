package com.rizalanggoro.presensigo.presentation.pages.home.teacher.general

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.GeneralAttendance
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(
    val status: StateStatus = StateStatus.Initial,
    val message: String = "",

    val attendances: List<GeneralAttendance> = emptyList()
)

class TeacherHomeGeneralAttendanceViewModel(
    private val attendanceApi: AttendanceApi
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    init {
        getAllGeneralAttendances()
    }

    fun getAllGeneralAttendances() = viewModelScope.launch {
        try {
            _state.update { it.copy(status = StateStatus.Loading) }

            val body = attendanceApi.getAllGeneralAttendances().body()

            _state.update {
                it.copy(
                    status = StateStatus.Success,
                    attendances = body.generalAttendances
                )
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _state.value = _state.value.copy(
                status = StateStatus.Failure,
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            _state.value = _state.value.copy(
                status = StateStatus.Failure,
                message = "Terjadi kesalahan tak terduga!"
            )
        }
    }
}