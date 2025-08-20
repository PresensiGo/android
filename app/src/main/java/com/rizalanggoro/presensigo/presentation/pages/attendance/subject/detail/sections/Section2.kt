package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.rizalanggoro.presensigo.core.constants.attendanceStatuses
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.core.extensions.isAfterDateTime
import com.rizalanggoro.presensigo.openapi.models.ConstantsAttendanceStatus
import com.rizalanggoro.presensigo.openapi.models.GetAllSubjectAttendanceRecordsItem
import com.rizalanggoro.presensigo.openapi.models.Student
import com.rizalanggoro.presensigo.presentation.components.PrimaryButton
import com.rizalanggoro.presensigo.presentation.components.SecondaryButton
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.DetailSubjectAttendanceViewModel
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private data class SelectedStudent(
    val student: Student,
    val status: AppAttendanceStatus? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section2() {
    val viewModel = koinViewModel<DetailSubjectAttendanceViewModel>()
    val attendanceState by viewModel.attendanceState.collectAsState()
    val recordsState by viewModel.recordsState.collectAsState()
    val createRecordState by viewModel.createRecordState.collectAsState()
    val deleteRecordState by viewModel.deleteRecordState.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var selectedStudent by remember { mutableStateOf<SelectedStudent?>(null) }
    var selectedStudentForDelete by remember {
        mutableStateOf<GetAllSubjectAttendanceRecordsItem?>(
            null
        )
    }

    LaunchedEffect(createRecordState) {
        if (createRecordState.isSuccess()) {
            scope.launch { sheetState.hide() }
                .invokeOnCompletion {
                    selectedStudent = null
                    viewModel.getAllRecords()
                }
            viewModel.resetCreateRecordState()
        }
    }

    LaunchedEffect(deleteRecordState) {
        if (deleteRecordState.isSuccess()) {
            selectedStudentForDelete = null
            viewModel.getAllRecords()
            viewModel.resetDeleteRecordState()
        }
    }

    PullToRefreshBox(
        isRefreshing = recordsState.isLoading(),
        onRefresh = { viewModel.getAllRecords() }
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
                            Item(
                                data = it, isLate = when (attendanceState.isSuccess()) {
                                    true -> it.record.dateTime.isAfterDateTime(
                                        (attendanceState as UiState.Success)
                                            .data
                                            .subjectAttendance
                                            .dateTime
                                    )

                                    else -> false
                                }
                            ) { selectedStudentForDelete = it }
                        }

                    else -> items(3) { Item(isLoading = true) }
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
                            Item(data = it) { selectedStudentForDelete = it }
                        }

                    else -> items(3) {
                        Item(isLoading = true)
                    }
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
                            Item(data = it) { selectedStudentForDelete = it }
                        }

                    else -> items(3) { Item(isLoading = true) }
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
                            Item(data = it) {
                                selectedStudent = SelectedStudent(
                                    student = it.student
                                )
                            }
                        }

                    else -> items(3) { Item(isLoading = true) }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // bottom sheets
        if (selectedStudent != null)
            ModalBottomSheet(
                containerColor = MaterialTheme.colorScheme.background,
                onDismissRequest = {
                    selectedStudent = null
                },
                sheetState = sheetState
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    OutlinedCard(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                selectedStudent!!.student.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                selectedStudent!!.student.nis,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                            )
                        }
                    }

                    Column {
                        attendanceStatuses.mapIndexed { index, it ->
                            ListItem(
                                leadingContent = {
                                    RadioButton(
                                        selected = selectedStudent!!.status == it.appStatus,
                                        onClick = null
                                    )
                                },
                                headlineContent = { Text(it.title) },
                                modifier = Modifier
                                    .clickable {
                                        selectedStudent = selectedStudent!!.copy(
                                            status = it.appStatus
                                        )
                                    }
                                    .padding(horizontal = 8.dp),
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 24.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        PrimaryButton(
                            text = "Simpan",
                            isLoading = createRecordState.isLoading()
                        ) {
                            if (selectedStudent!!.status != null)
                                viewModel.createRecord(
                                    studentId = selectedStudent!!.student.id,
                                    status = selectedStudent!!.status!!
                                )
                        }
                        SecondaryButton(
                            text = "Batal",
                        ) {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    selectedStudent = null
                                }
                            }
                        }
                    }
                }
            }

        // dialogs
        if (selectedStudentForDelete != null)
            AlertDialog(
                onDismissRequest = {
                    if (!deleteRecordState.isLoading())
                        selectedStudentForDelete = null
                },
                title = { Text("Konfirmasi Hapus") },
                text = { Text("Apakah Anda yakin akan menghapus presensi dari ${selectedStudentForDelete!!.student.name}?") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteRecord(
                                recordId = selectedStudentForDelete!!.record.id
                            )
                        },
                        enabled = !deleteRecordState.isLoading()
                    ) {
                        if (deleteRecordState.isLoading()) SmallCircularProgressIndicator()
                        else Text("Hapus")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { selectedStudentForDelete = null },
                        enabled = !deleteRecordState.isLoading()
                    ) {
                        Text("Batal")
                    }
                }
            )
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

@Composable
private fun Item(
    isLoading: Boolean = false,
    isLate: Boolean = false,
    data: GetAllSubjectAttendanceRecordsItem? = null,
    onClick: () -> Unit = {}
) {
    val isAttended = (data?.record?.id ?: 0) > 0

    Row(
        modifier = Modifier
            .clickable(enabled = isLoading == false) { onClick() }
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .then(
                    when (isLoading) {
                        true -> Modifier
                            .shimmer()
                            .background(MaterialTheme.colorScheme.outlineVariant)

                        else -> Modifier.background(
                            when (isAttended) {
                                true -> when (data?.record?.status == ConstantsAttendanceStatus.AttendanceStatusPresent && isLate) {
                                    true -> MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .48f)
                                    else -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .48f)
                                }

                                else -> MaterialTheme.colorScheme.errorContainer
                            }
                        )
                    }
                )
        ) {
            if (!isLoading)
                Icon(
                    when (isAttended) {
                        true -> Icons.Rounded.Person
                        else -> Icons.Rounded.Close
                    },
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = when (isAttended) {
                        true -> when (data?.record?.status == ConstantsAttendanceStatus.AttendanceStatusPresent && isLate) {
                            true -> MaterialTheme.colorScheme.tertiaryContainer
                            else -> MaterialTheme.colorScheme.primaryContainer
                        }

                        else -> MaterialTheme.colorScheme.onErrorContainer
                    }
                )
        }
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(
                when (isLoading) {
                    true -> 4.dp
                    else -> 0.dp
                }
            )
        ) {
            Text(
                data?.student?.name ?: "loading nama siswa",
                style = MaterialTheme.typography.titleMedium,
                modifier = when (isLoading) {
                    true -> Modifier
                        .shimmer()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.outlineVariant)

                    else -> Modifier
                },
                color = when (isLoading) {
                    true -> MaterialTheme.colorScheme.outlineVariant
                    else -> MaterialTheme.colorScheme.onBackground
                }
            )
            Text(
                data?.student?.nis ?: "loading nis siswa",
                style = MaterialTheme.typography.bodySmall,
                modifier = when (isLoading) {
                    true -> Modifier
                        .shimmer()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.outlineVariant)

                    else -> Modifier
                },
                color = when (isLoading) {
                    true -> MaterialTheme.colorScheme.outlineVariant
                    else -> MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                }
            )
            if (isAttended) {
                Text(
                    "${data?.record?.dateTime?.formatDateTime("EEEE, d MMMM yyyy") ?: "-"} - ${
                        data?.record?.dateTime?.formatDateTime(
                            "HH:mm"
                        ) ?: "-"
                    }",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                )
            }
        }
    }
}