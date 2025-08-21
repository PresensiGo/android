package com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.constants.AppAttendanceStatus
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.core.extensions.isAfterDateTime
import com.rizalanggoro.presensigo.openapi.models.Student
import com.rizalanggoro.presensigo.presentation.pages.attendance.components.AttendanceRecordItem
import com.rizalanggoro.presensigo.presentation.pages.attendance.components.CreateAttendanceRecordBottomSheet
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.DetailGeneralAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.sections.components.FilterBottomSheet
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private data class CreateRecord(
    val student: Student,
    val status: AppAttendanceStatus? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section2() {
    val viewModel = koinViewModel<DetailGeneralAttendanceViewModel>()
    val attendanceState by viewModel.attendance.collectAsState()
    val recordsState by viewModel.attendanceRecords.collectAsState()
    val selectedClassroom by viewModel.selectedClassroom.collectAsState()
    val isFilterOpen by viewModel.isFilterOpen.collectAsState()
    val createRecordState by viewModel.createRecordState.collectAsState()
//    val deleteRecordState by viewModel.deleteRecordState.collectAsState()

    var selectedForCreateRecord by remember { mutableStateOf<CreateRecord?>(null) }

    val scope = rememberCoroutineScope()
    val sheetStateForCreateRecord = rememberModalBottomSheetState(skipPartiallyExpanded = true)

//    var selectedStudent by remember { mutableStateOf<SelectedStudent?>(null) }
//    var selectedStudentForDelete by remember {
//        mutableStateOf<GetAllSubjectAttendanceRecordsItem?>(
//            null
//        )
//    }

    LaunchedEffect(createRecordState) {
        if (createRecordState.isSuccess()) {
            scope.launch { sheetStateForCreateRecord.hide() }
                .invokeOnCompletion {
                    selectedForCreateRecord = null
                    if (selectedClassroom != null) {
                        viewModel.getAttendanceRecords(
                            classroomId = selectedClassroom!!.classroom.id
                        )
                    }
                }
            viewModel.resetCreateRecord()
        }
    }
//
//    LaunchedEffect(deleteRecordState) {
//        if (deleteRecordState.isSuccess()) {
//            selectedStudentForDelete = null
//            viewModel.getAllRecords()
//            viewModel.resetDeleteRecordState()
//        }
//    }

    PullToRefreshBox(
        isRefreshing = recordsState.isLoading(),
        onRefresh = {
            if (selectedClassroom != null)
                viewModel.getAttendanceRecords(
                    classroomId = selectedClassroom!!.classroom.id
                )
        }
    ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    "Hadir",
                    style = MaterialTheme.typography.titleSmall,
                    color = when (recordsState.isLoading()) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground
                    },
                    modifier = Modifier
                        .padding(start = 24.dp, bottom = 8.dp)
                        .then(
                            when (recordsState.isLoading()) {
                                true -> Modifier
                                    .clip(CircleShape)
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                            }
                        )
                )
            }
            with(recordsState) {
                when (this) {
                    is UiState.Success ->
                        if (data.presentItems.isEmpty()) item { Empty() }
                        else items(data.presentItems) {
                            val isLate = when (attendanceState.isSuccess()) {
                                true -> it.record.dateTime.isAfterDateTime(
                                    (attendanceState as UiState.Success)
                                        .data
                                        .generalAttendance
                                        .datetime
                                )

                                else -> false
                            }

                            AttendanceRecordItem(
                                isAttended = it.record.id > 0,
                                isLate = isLate,
                                studentName = it.student.name,
                                studentNIS = it.student.nis,
                                recordDateTime = it.record.dateTime,
                                recordStatus = it.record.status
                            ) {/* selectedStudentForDelete = it*/ }
                        }

                    else -> items(3) { AttendanceRecordItem(isLoading = true) }
                }
            }

            item {
                Text(
                    "Sakit",
                    style = MaterialTheme.typography.titleSmall,
                    color = when (recordsState.isLoading()) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground
                    },
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp, bottom = 8.dp)
                        .then(
                            when (recordsState.isLoading()) {
                                true -> Modifier
                                    .clip(CircleShape)
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                            }
                        )
                )
            }
            with(recordsState) {
                when (this) {
                    is UiState.Success ->
                        if (data.sickItems.isEmpty()) item { Empty() }
                        else items(data.sickItems) {
                            AttendanceRecordItem(
                                isAttended = it.record.id > 0,
                                studentName = it.student.name,
                                studentNIS = it.student.nis,
                                recordDateTime = it.record.dateTime,
                                recordStatus = it.record.status
                            ) { /*selectedStudentForDelete = it*/ }
                        }

                    else -> items(3) { AttendanceRecordItem(isLoading = true) }
                }
            }

            item {
                Text(
                    "Izin",
                    style = MaterialTheme.typography.titleSmall,
                    color = when (recordsState.isLoading()) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground
                    },
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp, bottom = 8.dp)
                        .then(
                            when (recordsState.isLoading()) {
                                true -> Modifier
                                    .clip(CircleShape)
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                            }
                        )
                )
            }
            with(recordsState) {
                when (this) {
                    is UiState.Success ->
                        if (data.permissionItems.isEmpty()) item { Empty() }
                        else items(data.permissionItems) {
                            AttendanceRecordItem(
                                isAttended = it.record.id > 0,
                                studentName = it.student.name,
                                studentNIS = it.student.nis,
                                recordDateTime = it.record.dateTime,
                                recordStatus = it.record.status
                            ) { /*selectedStudentForDelete = it*/ }
                        }

                    else -> items(3) { AttendanceRecordItem(isLoading = true) }
                }
            }

            item {
                Text(
                    "Belum Presensi",
                    style = MaterialTheme.typography.titleSmall,
                    color = when (recordsState.isLoading()) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground
                    },
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp, bottom = 8.dp)
                        .then(
                            when (recordsState.isLoading()) {
                                true -> Modifier
                                    .clip(CircleShape)
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                            }
                        )
                )
            }
            with(recordsState) {
                when (this) {
                    is UiState.Success ->
                        if (data.alphaItems.isEmpty()) item { Empty() }
                        else items(data.alphaItems) {
                            AttendanceRecordItem(
                                isAttended = it.record.id > 0,
                                studentName = it.student.name,
                                studentNIS = it.student.nis,
                                recordDateTime = it.record.dateTime,
                                recordStatus = it.record.status
                            ) {
                                selectedForCreateRecord = CreateRecord(
                                    student = it.student
                                )
                            }
                        }

                    else -> items(3) { AttendanceRecordItem(isLoading = true) }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // bottom sheets
        if (isFilterOpen) {
            FilterBottomSheet()
        }

        if (selectedForCreateRecord != null) {
            CreateAttendanceRecordBottomSheet(
                isLoading = createRecordState.isLoading(),
                studentName = selectedForCreateRecord!!.student.name,
                studentNIS = selectedForCreateRecord!!.student.nis,
                status = selectedForCreateRecord!!.status,
                onClickStatus = {
                    selectedForCreateRecord = selectedForCreateRecord!!.copy(
                        status = it
                    )
                },
                onClickSave = {
                    if (selectedForCreateRecord!!.status != null) {
                        viewModel.createRecord(
                            studentId = selectedForCreateRecord!!.student.id,
                            status = selectedForCreateRecord!!.status!!
                        )
                    }
                },
                sheetState = sheetStateForCreateRecord,
                onDismissRequest = {
                    selectedForCreateRecord = null
                }
            )
        }

        // dialogs
//        if (selectedStudentForDelete != null)
//            AlertDialog(
//                onDismissRequest = {
//                    if (!deleteRecordState.isLoading())
//                        selectedStudentForDelete = null
//                },
//                title = { Text("Konfirmasi Hapus") },
//                text = { Text("Apakah Anda yakin akan menghapus presensi dari ${selectedStudentForDelete!!.student.name}?") },
//                confirmButton = {
//                    Button(
//                        onClick = {
//                            viewModel.deleteRecord(
//                                recordId = selectedStudentForDelete!!.record.id
//                            )
//                        },
//                        enabled = !deleteRecordState.isLoading()
//                    ) {
//                        if (deleteRecordState.isLoading()) SmallCircularProgressIndicator()
//                        else Text("Hapus")
//                    }
//                },
//                dismissButton = {
//                    TextButton(
//                        onClick = { selectedStudentForDelete = null },
//                        enabled = !deleteRecordState.isLoading()
//                    ) {
//                        Text("Batal")
//                    }
//                }
//            )
    }
}

@Composable
private fun Empty() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Rounded.PersonOff,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
        Text(
            "Tidak ada data",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
        )
    }
}