package com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.core.qr.QrGenerator
import com.rizalanggoro.presensigo.domain.QrData
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
    val action: Action = Action.Initial,
    val message: String = "",

    val attendance: GeneralAttendance? = null,
    val qrCodeBitmap: Bitmap? = null
) {
    enum class Action {
        Initial, GetGeneralAttendance, GetAllGeneralAttendanceRecords
    }
}

class DetailGeneralAttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val attendanceApi: AttendanceApi
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.General.Detail>()

    init {
        getGeneralAttendance()
    }

    fun getGeneralAttendance() = viewModelScope.launch {
        try {
            _state.update {
                it.copy(
                    status = StateStatus.Loading,
                    action = State.Action.GetGeneralAttendance
                )
            }

            val body = attendanceApi.getGeneralAttendance(
                generalAttendanceId = params.attendanceId
            ).body()

            // generate qrcode
            val qrCodeData = Gson().toJson(
                QrData(
                    type = "general",
                    code = body.generalAttendance.code
                )
            )

            _state.update {
                it.copy(
                    status = StateStatus.Success,
                    action = State.Action.GetGeneralAttendance,
                    attendance = body.generalAttendance,
                    qrCodeBitmap = QrGenerator.generateBitmap(qrCodeData)
                )
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _state.value = _state.value.copy(
                status = StateStatus.Failure,
                action = State.Action.GetGeneralAttendance,
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            _state.value = _state.value.copy(
                status = StateStatus.Failure,
                action = State.Action.GetGeneralAttendance,
                message = "Terjadi kesalahan tak terduga!"
            )
        }
    }
}