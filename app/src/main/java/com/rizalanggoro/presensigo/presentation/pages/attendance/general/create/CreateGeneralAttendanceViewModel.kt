package com.rizalanggoro.presensigo.presentation.pages.attendance.general.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.CreateGeneralAttendanceReq
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

data class State(
    val status: StateStatus = StateStatus.Initial,
    val message: String = ""
)

class CreateGeneralAttendanceViewModel(
    private val attendanceApi: AttendanceApi
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    @OptIn(ExperimentalMaterial3Api::class)
    fun create(date: Long, time: TimePickerState, note: String) = viewModelScope.launch {
        try {
            _state.update {
                it.copy(
                    status = StateStatus.Loading,
                )
            }

            val dateStr = date.formatDateTime("yyyy-MM-dd")
            val timeStr = String.format(
                Locale("id", "ID"), "%02d:%02d:00",
                time.hour, time.minute
            )
            val datetime = "$dateStr $timeStr"
            attendanceApi.createGeneralAttendance(
                CreateGeneralAttendanceReq(
                    datetime = datetime,
                    note = note
                )
            )

            _state.update {
                it.copy(
                    status = StateStatus.Success,
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