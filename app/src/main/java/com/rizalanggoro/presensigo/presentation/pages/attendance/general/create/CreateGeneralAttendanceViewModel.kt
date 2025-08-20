package com.rizalanggoro.presensigo.presentation.pages.attendance.general.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.CreateGeneralAttendanceReq
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.create.State
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class CreateGeneralAttendanceViewModel(
    private val attendanceApi: AttendanceApi
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val state get() = _state.asStateFlow()

    @OptIn(ExperimentalMaterial3Api::class)
    fun create(date: Long, time: TimePickerState) = viewModelScope.launch {
        try {
            _state.update { UiState.Loading }

            val dateStr = date.formatDateTime("yyyy-MM-dd")
            val timeStr = String.format(
                Locale("id", "ID"), "%02d:%02d:00",
                time.hour, time.minute
            )
            val datetime = "$dateStr $timeStr"
            attendanceApi.createGeneralAttendance(
                CreateGeneralAttendanceReq(
                    datetime = datetime,
                )
            )

            _state.update { UiState.Success(Unit) }
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