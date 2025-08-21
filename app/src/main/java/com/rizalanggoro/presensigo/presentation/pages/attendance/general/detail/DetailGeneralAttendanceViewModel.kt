package com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail

import android.graphics.Bitmap
import android.icu.util.Calendar
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.constants.AppAttendanceStatus
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.toConstantsAttendanceStatus
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.core.failure.toFailure
import com.rizalanggoro.presensigo.core.qr.QrGenerator
import com.rizalanggoro.presensigo.domain.QrData
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.apis.BatchApi
import com.rizalanggoro.presensigo.openapi.apis.ClassroomApi
import com.rizalanggoro.presensigo.openapi.apis.MajorApi
import com.rizalanggoro.presensigo.openapi.models.ConstantsAttendanceStatus
import com.rizalanggoro.presensigo.openapi.models.CreateGeneralAttendanceRecordReq
import com.rizalanggoro.presensigo.openapi.models.GeneralAttendance
import com.rizalanggoro.presensigo.openapi.models.GetAllBatchesItem
import com.rizalanggoro.presensigo.openapi.models.GetAllBatchesRes
import com.rizalanggoro.presensigo.openapi.models.GetAllClassroomsByMajorIdItem
import com.rizalanggoro.presensigo.openapi.models.GetAllClassroomsByMajorIdRes
import com.rizalanggoro.presensigo.openapi.models.GetAllGeneralAttendanceRecordsByClassroomIdItem
import com.rizalanggoro.presensigo.openapi.models.GetAllGeneralAttendanceRecordsItem
import com.rizalanggoro.presensigo.openapi.models.GetAllMajorsByBatchIdItem
import com.rizalanggoro.presensigo.openapi.models.GetAllMajorsByBatchIdRes
import com.rizalanggoro.presensigo.openapi.models.GetGeneralAttendanceRes
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Deprecated("")
data class State(
    val getAttendanceStatus: StateStatus = StateStatus.Initial,
    val attendance: GeneralAttendance? = null,
    val getAttendanceMessage: String = "",

    val getAttendanceRecordsStatus: StateStatus = StateStatus.Initial,
    val records: List<GetAllGeneralAttendanceRecordsItem> = emptyList(),
    val getAttendanceRecordsMessage: String = "",

    val deleteStatus: StateStatus = StateStatus.Initial,
    val deleteMessage: String = "",

    val qrCodeBitmap: Bitmap? = null
)

data class RecordsState(
    val presentItems: List<GetAllGeneralAttendanceRecordsByClassroomIdItem> = emptyList(),
    val sickItems: List<GetAllGeneralAttendanceRecordsByClassroomIdItem> = emptyList(),
    val permissionItems: List<GetAllGeneralAttendanceRecordsByClassroomIdItem> = emptyList(),
    val alphaItems: List<GetAllGeneralAttendanceRecordsByClassroomIdItem> = emptyList(),
)

class DetailGeneralAttendanceViewModel(
    savedStateHandle: SavedStateHandle,
    private val attendanceApi: AttendanceApi,
    private val batchApi: BatchApi,
    private val majorApi: MajorApi,
    private val classroomApi: ClassroomApi
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    private val _qrCode = MutableStateFlow<Bitmap?>(null)
    val qrCode get() = _qrCode.asStateFlow()

    private val _selectedBatch = MutableStateFlow<GetAllBatchesItem?>(null)
    val selectedBatch get() = _selectedBatch.asStateFlow()

    private val _selectedMajor = MutableStateFlow<GetAllMajorsByBatchIdItem?>(null)
    val selectedMajor get() = _selectedMajor.asStateFlow()

    private val _selectedClassroom = MutableStateFlow<GetAllClassroomsByMajorIdItem?>(null)
    val selectedClassroom get() = _selectedClassroom.asStateFlow()

    private val _isFilterOpen = MutableStateFlow(false)
    val isFilterOpen get() = _isFilterOpen.asStateFlow()

    private val _attendance = MutableStateFlow<UiState<GetGeneralAttendanceRes>>(UiState.Loading)
    val attendance get() = _attendance.asStateFlow()

    private val _batchesState = MutableStateFlow<UiState<GetAllBatchesRes>>(UiState.Loading)
    val batchesState get() = _batchesState.asStateFlow()

    private val _majorsState = MutableStateFlow<UiState<GetAllMajorsByBatchIdRes>>(UiState.Loading)
    val majorsState get() = _majorsState.asStateFlow()

    private val _classroomState = MutableStateFlow<UiState<GetAllClassroomsByMajorIdRes>>(
        UiState.Loading
    )
    val classroomState get() = _classroomState.asStateFlow()

    private val _attendanceRecords = MutableStateFlow<UiState<RecordsState>>(UiState.Loading)
    val attendanceRecords get() = _attendanceRecords.asStateFlow()

    val params = savedStateHandle.toRoute<Routes.Attendance.General.Detail>()

    init {
        getAttendance()
        getAllBatches()

        viewModelScope.launch {
            selectedBatch.collect {
                it.let {
                    if (it != null)
                        getAllMajors(batchId = it.batch.id)
                    else {
                        _attendanceRecords.update { UiState.Loading }
                        _selectedBatch.update { null }
                        _selectedMajor.update { null }
                        _selectedClassroom.update { null }
                    }
                }
            }
        }

        viewModelScope.launch {
            selectedMajor.collect {
                it.let {
                    val batch = selectedBatch.value
                    if (batch != null && it != null)
                        getAllClassrooms(
                            batchId = batch.batch.id,
                            majorId = it.major.id
                        )
                    else
                        _selectedClassroom.update { null }
                }
            }
        }

        viewModelScope.launch {
            selectedClassroom.collect {
                it.let {
                    if (it != null)
                        getAttendanceRecords(classroomId = it.classroom.id)
                }
            }
        }
    }

    fun getAllBatches() = viewModelScope.launch {
        try {
            _batchesState.update { UiState.Loading }

            val body = batchApi.getAllBatches().body()
            if (body.items.isNotEmpty()) {
                _selectedBatch.update { body.items.first() }
            }

            _batchesState.update { UiState.Success(body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _batchesState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _batchesState.update { UiState.Failure() }
        }
    }

    fun getAllMajors(batchId: Int) = viewModelScope.launch {
        try {
            _majorsState.update { UiState.Loading }

            val body = majorApi.getAllMajorsByBatchId(batchId = batchId).body()
            if (body.items.isNotEmpty()) {
                _selectedMajor.update { body.items.first() }
            }

            _majorsState.update { UiState.Success(body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _majorsState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _majorsState.update { UiState.Failure() }
        }
    }

    fun getAllClassrooms(batchId: Int, majorId: Int) = viewModelScope.launch {
        try {
            _classroomState.update { UiState.Loading }

            val body = classroomApi.getAllClassroomsByMajorId(
                batchId = batchId,
                majorId = majorId
            ).body()
            if (body.items.isNotEmpty()) {
                _selectedClassroom.update { body.items.first() }
            }

            _classroomState.update { UiState.Success(body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _classroomState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _classroomState.update { UiState.Failure() }
        }
    }

    fun getAttendance() = viewModelScope.launch {
        try {
            _attendance.update { UiState.Loading }
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

            _qrCode.update { QrGenerator.generateBitmap(qrCodeData) }
            _attendance.update { UiState.Success(data = body) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _attendance.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _attendance.update { UiState.Failure() }
        }
    }

    fun getAttendanceRecords(classroomId: Int) = viewModelScope.launch {
        try {
            _attendanceRecords.update { UiState.Loading }

            val body = attendanceApi.getAllGeneralAttendanceRecordsByClassroomId(
                generalAttendanceId = params.attendanceId,
                classroomId = classroomId
            ).body()

            val hasRecordItems = body.items.filter { it.record.id > 0 }

            _attendanceRecords.update {
                UiState.Success(
                    data = RecordsState(
                        presentItems = hasRecordItems.filter {
                            it.record.status == ConstantsAttendanceStatus.AttendanceStatusPresent
                        },
                        sickItems = hasRecordItems.filter {
                            it.record.status == ConstantsAttendanceStatus.AttendanceStatusSick
                        },
                        permissionItems = hasRecordItems.filter {
                            it.record.status == ConstantsAttendanceStatus.AttendanceStatusPermission
                        },
                        alphaItems = body.items.filter {
                            it.record.id == 0 || it.record.status == ConstantsAttendanceStatus.AttendanceStatusAlpha
                        }
                    )
                )
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _attendanceRecords.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _attendanceRecords.update { UiState.Failure() }
        }
    }

    fun deleteAttendance() = viewModelScope.launch {
        try {
            _state.update { it.copy(deleteStatus = StateStatus.Loading) }

            attendanceApi.deleteGeneralAttendance(params.attendanceId)

            _state.update { it.copy(deleteStatus = StateStatus.Success) }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    deleteStatus = StateStatus.Failure,
                    deleteMessage = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    deleteStatus = StateStatus.Failure,
                    deleteMessage = "Terjadi kesalahan tak terduga!"
                )
            }
        }
    }

    fun setSelectedBatch(batch: GetAllBatchesItem?) =
        _selectedBatch.update { batch }

    fun setSelectedMajor(major: GetAllMajorsByBatchIdItem?) =
        _selectedMajor.update { major }

    fun setSelectedClassroom(classroom: GetAllClassroomsByMajorIdItem?) =
        _selectedClassroom.update { classroom }

    fun setFilterOpen(isOpen: Boolean) =
        _isFilterOpen.update { isOpen }

    private val _createRecordState = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val createRecordState get() = _createRecordState.asStateFlow()
    fun createRecord(studentId: Int, status: AppAttendanceStatus) = viewModelScope.launch {
        try {
            if (attendance.value is UiState.Success) {
                val attendance = (attendance.value as UiState.Success).data.generalAttendance

                _createRecordState.update { UiState.Loading }
                attendanceApi.createGeneralAttendanceRecord(
                    generalAttendanceId = params.attendanceId,
                    body = CreateGeneralAttendanceRecordReq(
                        datetime = when (status) {
                            AppAttendanceStatus.Present -> attendance.datetime.formatDateTime("yyyy-MM-dd HH:mm:ss")
                            else -> Calendar.getInstance().timeInMillis.formatDateTime("yyyy-MM-dd HH:mm:ss")
                        },
                        status = status.toConstantsAttendanceStatus(),
                        studentId = studentId
                    )
                )
                _createRecordState.update { UiState.Success(Unit) }
            }
        } catch (e: ResponseException) {
            e.printStackTrace()
            _createRecordState.update {
                UiState.Failure(
                    message = e.response.bodyAsText().toFailure().message
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _createRecordState.update { UiState.Failure() }
        }
    }

    fun resetCreateRecord() = _createRecordState.update { UiState.Initial }
}